package net.archiloque.tacticalnexus.solver.entities.play

import net.archiloque.tacticalnexus.solver.entities.input.InputEntity
import net.archiloque.tacticalnexus.solver.entities.input.InputEntityType
import net.archiloque.tacticalnexus.solver.entities.input.Item
import net.archiloque.tacticalnexus.solver.entities.input.PlayerStartPosition
import net.archiloque.tacticalnexus.solver.entities.input.Staircase
import net.archiloque.tacticalnexus.solver.entities.input.Tower
import net.archiloque.tacticalnexus.solver.entities.input.Wall

class PlayableTower(
    val entitiesCount: Int,
    val playEntities: Array<PlayEntity>,
    val startingPositionPosition: Position,
    val reachableByStartingPosition: IntArray,
    val reachable: Array<IntArray>,
    val roomsSingleDoor: IntArray,
) {
    companion object {
        fun prepare(tower: Tower): PlayableTower {
            val entities = mutableListOf<PlayEntity>()
            val entitiesPositions = mutableMapOf<Position, PlayEntity>()
            val entitiesIndexByPosition = mutableMapOf<Position, Int>()

            findItemGroups(tower, entities, entitiesPositions, entitiesIndexByPosition)
            return findOtherEntities(tower, entities, entitiesPositions, entitiesIndexByPosition)
        }

        private fun toPlay(inputEntity: InputEntity, index: Int, position: Position): PlayEntity {
            return when (inputEntity.getType()) {
                InputEntityType.Door -> {
                    inputEntity as net.archiloque.tacticalnexus.solver.entities.input.Door
                    Door(inputEntity.color, index, position)
                }

                InputEntityType.Enemy -> {
                    inputEntity as net.archiloque.tacticalnexus.solver.entities.input.Enemy
                    Enemy.enemy(inputEntity, index, position)
                }

                InputEntityType.Exit -> {
                    Exit(index, position)
                }

                InputEntityType.Key -> {
                    inputEntity as net.archiloque.tacticalnexus.solver.entities.input.Key
                    Key(inputEntity.color, index, position)
                }

                else -> {
                    throw IllegalStateException("Should not happen $inputEntity")
                }
            }
        }

        private fun findItemGroups(
            tower: Tower,
            entities: MutableList<PlayEntity>,
            entitiesPositions: MutableMap<Position, PlayEntity>,
            entitiesIndexByPosition: MutableMap<Position, Int>,
        ) {
            forEachEntity(tower) { levelIndex: Int, lineIndex: Int, columnIndex: Int, initialEntity: InputEntity ->
                if (initialEntity.getType() == InputEntityType.Item) {
                    initialEntity as Item
                    val initialItemPosition = Position(levelIndex, lineIndex, columnIndex)
                    if (!entitiesPositions.containsKey(initialItemPosition)) {
                        findItemGroup(
                            initialItemPosition,
                            initialEntity,
                            tower,
                            entities,
                            entitiesPositions,
                            entitiesIndexByPosition
                        )
                    }
                }
            }
        }

        private fun findItemGroup(
            initialItemPosition: Position,
            initialEntity: Item,
            tower: Tower,
            entities: MutableList<PlayEntity>,
            entitiesPositions: MutableMap<Position, PlayEntity>,
            entitiesIndexByPosition: MutableMap<Position, Int>,
        ) {
            var positionsToCheck = mutableSetOf<Position>()
            val exploredPositions = mutableSetOf<Position>()
            val foundItems = mutableSetOf<PositionedItem>()
            positionsToCheck.add(initialItemPosition)
            exploredPositions.add(initialItemPosition)
            foundItems.add(
                PositionedItem(
                    initialEntity,
                    initialItemPosition
                )
            )

            while (positionsToCheck.isNotEmpty()) {
                val newPositionsToCheck = mutableSetOf<Position>()
                for (positionToCheck in positionsToCheck) {
                    aroundPosition(positionToCheck, tower) { newPosition ->
                        if (exploredPositions.add(newPosition)) {
                            val newEntity =
                                tower.levels()[newPosition.level].entities[newPosition.line][newPosition.column]
                            if (newEntity == null) {
                                newPositionsToCheck.add(newPosition)
                            } else if (newEntity.getType() == InputEntityType.Item) {
                                newEntity as Item
                                foundItems.add(
                                    PositionedItem(
                                        newEntity,
                                        newPosition
                                    )
                                )
                                newPositionsToCheck.add(newPosition)
                            }
                        }
                    }
                }
                positionsToCheck = newPositionsToCheck
            }

            val itemGroup = ItemGroup(entities.size, foundItems.toTypedArray())
            for (foundItem in foundItems) {
                entitiesPositions[foundItem.position] = itemGroup
                entitiesIndexByPosition[foundItem.position] = entities.size
            }
            entities.add(itemGroup)
        }

        private fun findOtherEntities(
            tower: Tower,
            entities: MutableList<PlayEntity>,
            entitiesPositions: MutableMap<Position, PlayEntity>,
            entitiesIndexByPosition: MutableMap<Position, Int>,
        ): PlayableTower {
            var startingPositionPosition: Position? = null
            val upStaircasesIndexByLevel = mutableMapOf<Int, Int>()
            val downStaircasesPositionByLevel = mutableMapOf<Int, Position>()

            forEachEntity(tower) { levelIndex: Int, lineIndex: Int, columnIndex: Int, entity: InputEntity ->
                when (entity.getType()) {
                    InputEntityType.Wall -> {}
                    InputEntityType.Item -> {}
                    InputEntityType.Staircase -> {
                        entity as Staircase
                        when (entity.direction) {
                            Staircase.StaircaseDirection.up -> {
                                val position = Position(levelIndex, lineIndex, columnIndex)
                                val upStaircaseEntity = UpStaircase(entities.size, position)

                                entitiesIndexByPosition[position] = entities.size
                                upStaircasesIndexByLevel[levelIndex] = entities.size
                                entities.add(upStaircaseEntity)
                                entitiesPositions[position] = upStaircaseEntity

                            }

                            Staircase.StaircaseDirection.down -> {
                                downStaircasesPositionByLevel[levelIndex] =
                                    Position(levelIndex, lineIndex, columnIndex)
                            }
                        }
                    }

                    InputEntityType.PlayerStartPosition -> {
                        startingPositionPosition = Position(levelIndex, lineIndex, columnIndex)
                    }

                    else -> {
                        val position = Position(levelIndex, lineIndex, columnIndex)
                        val playEntity = toPlay(entity, entities.size, position)

                        entitiesIndexByPosition[position] = entities.size
                        entities.add(playEntity)
                        entitiesPositions[position] = playEntity
                    }
                }
            }

            val reachableEntities = entities.map { entity ->
                findReacheableEntities(
                    entity.getPositions(),
                    tower,
                    entitiesIndexByPosition
                )
            }.toTypedArray()

            // Up staircase use the reachable entities of the corresponding down staircase
            upStaircasesIndexByLevel.forEach { (levelIndex, upStaircaseIndex) ->
                val downStaircasePosition = downStaircasesPositionByLevel[levelIndex + 1]!!
                val reachableByDownStaircase =
                    findReacheableEntities(arrayOf(downStaircasePosition), tower, entitiesIndexByPosition)
                reachableEntities[upStaircaseIndex] = reachableByDownStaircase
            }

            val roomsSingleDoor = mutableListOf<Int>()
            entities.forEach { entity ->
                if (entity.getType() == PlayEntityType.ItemGroup) {
                    entity as ItemGroup

                    var positionsToCheck = mutableSetOf<Position>()
                    val exploredPositions = mutableSetOf<Position>()
                    val entitiesAroundType = mutableSetOf<InputEntityType>()
                    val entitiesAroundPosition = mutableSetOf<Position>()
                    positionsToCheck.addAll(entity.getPositions())
                    exploredPositions.addAll(entity.getPositions())

                    while (positionsToCheck.isNotEmpty()) {
                        val newPositionsToCheck = mutableSetOf<Position>()
                        for (positionToCheck in positionsToCheck) {
                            aroundPosition(positionToCheck, tower) { newPosition ->
                                if (exploredPositions.add(newPosition)) {
                                    val newEntity =
                                        tower.levels()[newPosition.level].entities[newPosition.line][newPosition.column]
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
                findReacheableEntities(arrayOf(startingPositionPosition!!), tower, entitiesIndexByPosition),
                reachableEntities,
                roomsSingleDoor.toIntArray()
            )
        }

        private fun forEachEntity(
            tower: Tower,
            callback: (levelIndex: Int, lineIndex: Int, columnIndex: Int, entity: InputEntity) -> Unit,
        ) {
            tower.levels().forEachIndexed { levelIndex, level ->
                for (lineIndex in 0..<level.lines) {
                    for (columnIndex in 0..<level.columns) {
                        val inputEntity = level.entities[lineIndex][columnIndex]
                        if (inputEntity != null) {
                            callback(levelIndex, lineIndex, columnIndex, inputEntity)
                        }
                    }
                }
            }
        }

        private fun aroundPosition(positionToCheck: Position, tower: Tower, callback: (position: Position) -> Unit) {
            if (positionToCheck.column > 0) {
                callback(Position(positionToCheck.level, positionToCheck.line, positionToCheck.column - 1))
            }
            if (positionToCheck.column < (tower.levels()[positionToCheck.level].columns - 1)) {
                callback(Position(positionToCheck.level, positionToCheck.line, positionToCheck.column + 1))
            }
            if (positionToCheck.line > 0) {
                callback(Position(positionToCheck.level, positionToCheck.line - 1, positionToCheck.column))
            }
            if (positionToCheck.line < (tower.levels()[positionToCheck.level].lines - 1)) {
                callback(Position(positionToCheck.level, positionToCheck.line + 1, positionToCheck.column))
            }
        }

        private fun findReacheableEntities(
            positions: Array<Position>,
            tower: Tower,
            entitiesIndexByPosition: MutableMap<Position, Int>,
        ): IntArray {
            var positionsToCheck = mutableSetOf<Position>()
            val exploredPositions = mutableSetOf<Position>()

            positionsToCheck.addAll(positions)
            exploredPositions.addAll(positions)

            val reachableEntities = mutableSetOf<Int>()
            while (positionsToCheck.isNotEmpty()) {
                val newPositionsToCheck = mutableSetOf<Position>()
                for (positionToCheck in positionsToCheck) {
                    aroundPosition(positionToCheck, tower) { position ->
                        if (exploredPositions.add(position)) {
                            val entity = tower.levels()[position.level].entities[position.line][position.column]
                            if (entity == null) {
                                newPositionsToCheck.add(position)
                            } else if (
                                !(
                                        // Can't reach:
                                        // - walls
                                        // - starting position
                                        // - down staircase
                                        (entity == Wall.instance) || (entity == PlayerStartPosition.instance) ||
                                                ((entity.getType() == InputEntityType.Staircase) && ((entity as Staircase).direction == Staircase.StaircaseDirection.down)))
                            ) {
                                val entityIndex = entitiesIndexByPosition[position]!!
                                reachableEntities.add(entityIndex)
                            }
                        }
                    }
                }
                positionsToCheck = newPositionsToCheck
            }
            return reachableEntities.sorted().toIntArray()
        }


    }

    fun printAll() {
        for (entityIndex in 0..<entitiesCount) {
            val playEntity = playEntities[entityIndex]
            println("$entityIndex $playEntity")
        }
    }

}
