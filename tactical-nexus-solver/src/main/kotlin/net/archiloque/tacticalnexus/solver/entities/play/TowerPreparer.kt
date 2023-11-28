package net.archiloque.tacticalnexus.solver.entities.play

import net.archiloque.tacticalnexus.solver.entities.Direction
import net.archiloque.tacticalnexus.solver.entities.Position
import net.archiloque.tacticalnexus.solver.entities.input.InputEntity
import net.archiloque.tacticalnexus.solver.entities.input.InputEntityType
import net.archiloque.tacticalnexus.solver.entities.input.Item
import net.archiloque.tacticalnexus.solver.entities.input.Key
import net.archiloque.tacticalnexus.solver.entities.input.Staircase
import net.archiloque.tacticalnexus.solver.entities.input.Tower

const val LEVEL_DIMENSION_CELLS = 14

class TowerPreparer(private val tower: Tower) {

    private val entities = mutableListOf<PlayEntity>()
    private val entitiesPositions = mutableMapOf<Position, PlayEntity>()
    private val entitiesIndexByPosition = mutableMapOf<Position, Int>()

    fun prepare(): PlayableTower {
        findGoodiesGroups()
        findStandardEntities()
        return findOtherEntities()
    }

    private fun findGoodiesGroups(
    ) {
        forEachEntity() { levelIndex: Int, lineIndex: Int, columnIndex: Int, initialEntity: InputEntity ->
            when (initialEntity.getType()) {
                InputEntityType.Item -> {
                    initialEntity as Item
                    val initialItemPosition = Position(levelIndex, lineIndex, columnIndex)
                    if (!entitiesPositions.containsKey(initialItemPosition)) {
                        findGoodiesGroup(
                            initialItemPosition,
                            initialEntity,
                            null
                        )
                    }
                }

                InputEntityType.Key -> {
                    initialEntity as net.archiloque.tacticalnexus.solver.entities.input.Key
                    val initialItemPosition = Position(levelIndex, lineIndex, columnIndex)
                    if (!entitiesPositions.containsKey(initialItemPosition)) {
                        findGoodiesGroup(
                            initialItemPosition,
                            null,
                            initialEntity,
                        )
                    }
                }

                else -> {}
            }
        }
    }

    private fun findStandardEntities() {
        forEachEntity() { levelIndex: Int, lineIndex: Int, columnIndex: Int, entity: InputEntity ->
            val position = Position(levelIndex, lineIndex, columnIndex)
            val playEntity = when (entity.getType()) {
                InputEntityType.Door -> {
                    entity as net.archiloque.tacticalnexus.solver.entities.input.Door
                    Door(entity.color, entities.size, position)
                }

                InputEntityType.Enemy -> {
                    entity as net.archiloque.tacticalnexus.solver.entities.input.Enemy
                    Enemy.enemy(entity, entities.size, position)
                }

                InputEntityType.OneWay -> {
                    entity as net.archiloque.tacticalnexus.solver.entities.input.OneWay
                    OneWay(entity.direction, entities.size, position)
                }

                else -> {
                    null
                }
            }
            if (playEntity != null) {
                registerElement(position, playEntity)
                entities.add(playEntity)
            }
        }
    }

    private fun findOtherEntities(
    ): PlayableTower {
        var startingPositionPosition: Position? = null
        val upStaircasesIndexByLevel = mutableMapOf<Int, Int>()
        val downStaircasesPositionByLevel = mutableMapOf<Int, Position>()

        forEachEntity() { levelIndex: Int, lineIndex: Int, columnIndex: Int, entity: InputEntity ->
            when (entity.getType()) {

                InputEntityType.PlayerStartPosition -> {
                    startingPositionPosition = Position(levelIndex, lineIndex, columnIndex)
                }

                InputEntityType.Staircase -> {
                    entity as Staircase
                    when (entity.direction) {
                        Staircase.StaircaseDirection.up -> {
                            val position = Position(levelIndex, lineIndex, columnIndex)
                            val upStaircaseEntity = UpStaircase(entities.size, position)

                            registerElement(position, upStaircaseEntity)
                            upStaircasesIndexByLevel[levelIndex] = entities.size
                            entities.add(upStaircaseEntity)
                        }

                        Staircase.StaircaseDirection.down -> {
                            downStaircasesPositionByLevel[levelIndex] =
                                Position(levelIndex, lineIndex, columnIndex)
                        }
                    }
                }

                else -> {
                }
            }
        }

        val reachableEntities = entities.map { entity ->
            findReacheableEntities(
                entity.getPositions(),
            )
        }.toTypedArray()

        // Up staircase use the reachable entities of the corresponding down staircase
        upStaircasesIndexByLevel.forEach { (levelIndex, upStaircaseIndex) ->
            val downStaircasePosition = downStaircasesPositionByLevel[levelIndex + 1]!!
            val reachableByDownStaircase =
                findReacheableEntities(arrayOf(downStaircasePosition))
            reachableEntities[upStaircaseIndex] = reachableByDownStaircase
        }

        val roomsSingleDoor = mutableListOf<Int>()
        entities.forEach { entity ->
            if (entity.getType() == PlayEntityType.GoodiesGroup) {
                entity as GoodiesGroup

                var positionsToCheck = mutableSetOf<Position>()
                val exploredPositions = mutableSetOf<Position>()
                val entitiesAroundType = mutableSetOf<InputEntityType>()
                val entitiesAroundPosition = mutableSetOf<Position>()
                positionsToCheck.addAll(entity.getPositions())
                exploredPositions.addAll(entity.getPositions())

                while (positionsToCheck.isNotEmpty()) {
                    val newPositionsToCheck = mutableSetOf<Position>()
                    for (positionToCheck in positionsToCheck) {
                        aroundPosition(positionToCheck) { newPosition, _ ->
                            if (exploredPositions.add(newPosition)) {
                                val newEntity =
                                    tower.standardLevels()[newPosition.level].entities[newPosition.line][newPosition.column]
                                if (newEntity == null) {
                                    newPositionsToCheck.add(newPosition)
                                } else if (newEntity.getType() != InputEntityType.Wall) {
                                    entitiesAroundType.add(newEntity.getType())
                                    entitiesAroundPosition.add(newPosition)
                                }
                            }
                        }
                    }
                    positionsToCheck = newPositionsToCheck
                }

                if ((entitiesAroundType.size == 1) && (entitiesAroundType.first() == InputEntityType.Door)) {
                    val doorEntity = entities.indexOfFirst { playEntity ->
                        (playEntity.isDoor()) && playEntity.getPositions().first() == entitiesAroundPosition.first()
                    }
                    roomsSingleDoor.add(doorEntity)
                }
            }
        }

        return PlayableTower(
            entities.size,
            entities.toTypedArray(),
            startingPositionPosition!!,
            findReacheableEntities(arrayOf(startingPositionPosition!!)),
            reachableEntities,
            roomsSingleDoor.toIntArray(),
            entitiesIndexByPosition[tower.checkScore()]!!,
            entitiesIndexByPosition[tower.starScore()]!!,
            tower.levels(),
        )
    }

    private fun forEachEntity(
        callback: (levelIndex: Int, lineIndex: Int, columnIndex: Int, entity: InputEntity) -> Unit,
    ) {
        tower.standardLevels().forEachIndexed { levelIndex, level ->
            for (lineIndex in 0..LEVEL_DIMENSION_CELLS) {
                for (columnIndex in 0..LEVEL_DIMENSION_CELLS) {
                    val inputEntity = level.entities[lineIndex][columnIndex]
                    if (inputEntity != null) {
                        callback(levelIndex, lineIndex, columnIndex, inputEntity)
                    }
                }
            }
        }
    }

    private fun findReacheableEntities(
        positions: Array<Position>,
    ): IntArray {
        var positionsToCheck = mutableSetOf<Position>()
        val exploredPositions = mutableSetOf<Position>()

        positionsToCheck.addAll(positions)
        exploredPositions.addAll(positions)

        val reachableEntities = mutableSetOf<Int>()
        while (positionsToCheck.isNotEmpty()) {
            val newPositionsToCheck = mutableSetOf<Position>()
            for (positionToCheck in positionsToCheck) {
                aroundPosition(positionToCheck) { position, direction ->
                    if (exploredPositions.add(position)) {
                        val entity = tower.standardLevels()[position.level].entities[position.line][position.column]
                        if (entity == null) {
                            newPositionsToCheck.add(position)
                        } else {
                            when (entity.getType()) {
                                InputEntityType.OneWay -> {
                                    entity as net.archiloque.tacticalnexus.solver.entities.input.OneWay
                                    if (entity.direction == direction) {
                                        reachableEntities.add(entitiesIndexByPosition[position]!!)
                                    }
                                }

                                InputEntityType.PlayerStartPosition -> {
                                    newPositionsToCheck.add(position)
                                }

                                InputEntityType.Staircase -> {
                                    // Ignore down staircase
                                    entity as Staircase
                                    when (entity.direction) {
                                        Staircase.StaircaseDirection.up -> {
                                            reachableEntities.add(entitiesIndexByPosition[position]!!)
                                        }

                                        Staircase.StaircaseDirection.down -> {
                                        }
                                    }
                                }

                                InputEntityType.Wall -> {
                                }

                                else -> {
                                    reachableEntities.add(entitiesIndexByPosition[position]!!)
                                }
                            }
                        }
                    }
                }
            }
            positionsToCheck = newPositionsToCheck
        }
        return reachableEntities.sorted().toIntArray()
    }

    private fun findGoodiesGroup(
        initialElementPosition: Position,
        initialItem: Item?,
        initialKey: Key?,
    ) {
        var positionsToCheck = mutableSetOf<Position>()
        val exploredPositions = mutableSetOf<Position>()
        val foundItems = mutableSetOf<PositionedItem>()
        val foundKeys = mutableSetOf<PositionedKey>()
        positionsToCheck.add(initialElementPosition)
        exploredPositions.add(initialElementPosition)

        if (initialItem != null) {
            foundItems.add(
                PositionedItem(
                    initialItem,
                    initialElementPosition
                )
            )
        } else {
            foundKeys.add(
                PositionedKey(
                    initialKey!!.color,
                    initialElementPosition
                )
            )
        }

        while (positionsToCheck.isNotEmpty()) {
            val newPositionsToCheck = mutableSetOf<Position>()
            for (positionToCheck in positionsToCheck) {
                aroundPosition(positionToCheck) { newPosition, _ ->
                    if (exploredPositions.add(newPosition)) {
                        val newEntity =
                            tower.standardLevels()[newPosition.level].entities[newPosition.line][newPosition.column]
                        if (newEntity == null) {
                            newPositionsToCheck.add(newPosition)
                        } else {
                            when (newEntity.getType()) {
                                InputEntityType.Item -> {
                                    newEntity as Item
                                    foundItems.add(
                                        PositionedItem(
                                            newEntity,
                                            newPosition
                                        )
                                    )
                                    newPositionsToCheck.add(newPosition)
                                }

                                InputEntityType.Key -> {
                                    newEntity as net.archiloque.tacticalnexus.solver.entities.input.Key
                                    foundKeys.add(
                                        PositionedKey(
                                            newEntity.color,
                                            newPosition
                                        )
                                    )
                                    newPositionsToCheck.add(newPosition)
                                }

                                else -> {}
                            }
                        }
                    }
                }
            }
            positionsToCheck = newPositionsToCheck
        }

        val goodiesGroup = GoodiesGroup(
            entities.size,
            foundItems.toTypedArray(),
            foundKeys.toTypedArray(),
        )
        for (foundItem in foundItems) {
            registerElement(foundItem.position, goodiesGroup)
        }
        for (foundKey in foundKeys) {
            registerElement(foundKey.position, goodiesGroup)
        }
        entities.add(goodiesGroup)
    }

    private fun registerElement(
        position: Position,
        playEntity: PlayEntity,
    ) {
        entitiesPositions[position] = playEntity
        entitiesIndexByPosition[position] = entities.size
    }

    private fun aroundPosition(
        positionToCheck: Position,
        callback: (position: Position, direction: Direction) -> Unit,
    ) {
        if (positionToCheck.column > 0) {
            callback(Position(positionToCheck.level, positionToCheck.line, positionToCheck.column - 1), Direction.left)
        }
        if (positionToCheck.column < LEVEL_DIMENSION_CELLS) {
            callback(Position(positionToCheck.level, positionToCheck.line, positionToCheck.column + 1), Direction.right)
        }
        if (positionToCheck.line > 0) {
            callback(Position(positionToCheck.level, positionToCheck.line - 1, positionToCheck.column), Direction.up)
        }
        if (positionToCheck.line < LEVEL_DIMENSION_CELLS) {
            callback(Position(positionToCheck.level, positionToCheck.line + 1, positionToCheck.column), Direction.down)
        }
    }
}