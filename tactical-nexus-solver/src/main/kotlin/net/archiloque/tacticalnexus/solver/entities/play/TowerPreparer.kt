package net.archiloque.tacticalnexus.solver.entities.play

import net.archiloque.tacticalnexus.solver.entities.Direction
import net.archiloque.tacticalnexus.solver.entities.Position
import net.archiloque.tacticalnexus.solver.entities.StaircaseDirection
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
        forEachEntity { levelIndex: Int, lineIndex: Int, columnIndex: Int, initialEntity: InputEntity ->
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
                    initialEntity as Key
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
        forEachEntity { levelIndex: Int, lineIndex: Int, columnIndex: Int, entity: InputEntity ->
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
        val downStaircasesIndexByLevel = mutableMapOf<Int, Int>()

        forEachEntity { levelIndex: Int, lineIndex: Int, columnIndex: Int, entity: InputEntity ->
            when (entity.getType()) {

                InputEntityType.PlayerStartPosition -> {
                    startingPositionPosition = Position(levelIndex, lineIndex, columnIndex)
                }

                InputEntityType.Staircase -> {
                    entity as Staircase
                    val position = Position(levelIndex, lineIndex, columnIndex)
                    val staircaseEntity =
                        Staircase(entity.direction, entities.size, position)

                    registerElement(position, staircaseEntity)

                    when (entity.direction) {
                        StaircaseDirection.up -> {
                            upStaircasesIndexByLevel[levelIndex] = entities.size
                        }

                        StaircaseDirection.down -> {
                            downStaircasesIndexByLevel[levelIndex] = entities.size
                        }
                    }
                    entities.add(staircaseEntity)
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

        var reachableByStartingPosition = findReacheableEntities(arrayOf(startingPositionPosition!!))

        // Replace the up and down stairs by all the combined entities they can reach
        for (level in 0..<(tower.standardLevels().size - 1)) {
            val upStairCaseEntityIndex = upStaircasesIndexByLevel[level]!!
            val dowStairCaseEntityIndex = downStaircasesIndexByLevel[level + 1]!!
            val reachableElements =
                reachableEntities[upStairCaseEntityIndex] + reachableEntities[dowStairCaseEntityIndex]
            reachableEntities.forEachIndexed { elementIndex, elements ->
                val upStairIndex = elements.indexOf(upStairCaseEntityIndex)
                val downStairIndex = elements.indexOf(dowStairCaseEntityIndex)
                if ((upStairIndex != -1) || (downStairIndex != -1)) {
                    val patchedElements = mutableSetOf<Int>()
                    patchedElements.addAll(elements.asList())
                    if (upStairIndex != -1) {
                        patchedElements.remove(upStairCaseEntityIndex)
                        patchedElements.addAll(reachableElements.asList() - elementIndex)
                    } else {
                        patchedElements.remove(dowStairCaseEntityIndex)
                        patchedElements.addAll(reachableElements.asList() - elementIndex)
                    }
                    val patchedArray = patchedElements.toIntArray()
                    patchedArray.sort()
                    reachableEntities[elementIndex] = patchedArray
                }
            }

            val upStairIndex = reachableByStartingPosition.indexOf(upStairCaseEntityIndex)
            val downStairIndex = reachableByStartingPosition.indexOf(dowStairCaseEntityIndex)
            if ((upStairIndex != -1) || (downStairIndex != -1)) {
                val patchedElements = mutableSetOf<Int>()
                patchedElements.addAll(reachableByStartingPosition.asList())
                if (upStairIndex != -1) {
                    patchedElements.remove(upStairCaseEntityIndex)
                    patchedElements.addAll(reachableElements.asList())
                } else {
                    patchedElements.remove(dowStairCaseEntityIndex)
                    patchedElements.addAll(reachableElements.asList())
                }
                val patchedArray = patchedElements.toIntArray()
                patchedArray.sort()
                reachableByStartingPosition = patchedArray
            }


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
            reachableByStartingPosition,
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
                                    newEntity as Key
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