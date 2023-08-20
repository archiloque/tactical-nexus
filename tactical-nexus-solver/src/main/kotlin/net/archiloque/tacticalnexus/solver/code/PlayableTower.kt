package net.archiloque.tacticalnexus.solver.code

import net.archiloque.tacticalnexus.solver.entities.Entity
import net.archiloque.tacticalnexus.solver.entities.Level
import net.archiloque.tacticalnexus.solver.entities.Staircase
import net.archiloque.tacticalnexus.solver.entities.Tower
import net.archiloque.tacticalnexus.solver.entities.Wall

class PlayableTower(
    val entitiesNumber: Int,
    val positionedEntities: Array<PositionedEntity>,
    val entitiesIndexByPosition: Map<Position, Int>,
    val startingPosition: Int,
    val reachable: Array<Array<Int>>,
) {

    companion object {
        fun prepare(tower: Tower): PlayableTower {
            val entities = mutableListOf<PositionedEntity>()
            var startingPosition: Int = -1
            val upStaircasesIndexByLevel = mutableMapOf<Int, Int>()
            val downStaircasesPositionByLevel = mutableMapOf<Int, Position>()

            tower.levels().forEachIndexed { levelIndex, level ->
                for (line in 0..<level.lines) {
                    for (column in 0..<level.columns) {
                        val entity = level.entities[line][column]
                        if ((entity != null) && (entity.getType() != Entity.EntityType.Wall)) {
                            val entityPosition = Position(levelIndex, line, column)
                            val positionedEntity = PositionedEntity(entity, entityPosition)
                            val currentEntityIndex = entities.size

                            if (entity.getType() == Entity.EntityType.Staircase) {
                                when ((entity as Staircase).direction) {
                                    Staircase.StaircaseDirection.up -> {
                                        upStaircasesIndexByLevel[levelIndex] = currentEntityIndex
                                    }

                                    Staircase.StaircaseDirection.down -> {
                                        downStaircasesPositionByLevel[levelIndex] = entityPosition
                                    }
                                }
                            }

                            if (entity.getType() == Entity.EntityType.PlayerStartPosition) {
                                startingPosition = currentEntityIndex
                            }
                            if ((entity.getType() != Entity.EntityType.Staircase) || ((entity as Staircase).direction != Staircase.StaircaseDirection.down)) {
                                entities.add(positionedEntity)
                            }
                        }
                    }
                }
            }
            val positionedEntities = entities.toTypedArray()
            val entitiesIndexByPosition = mutableMapOf<Position, Int>()
            positionedEntities.forEachIndexed { index, positionedEntity ->
                entitiesIndexByPosition[positionedEntity.position] = index
            }
            val reachableEntities = positionedEntities.map { positionedEntity ->
                findReacheableEntities(
                    positionedEntity.position,
                    tower.levels(),
                    entitiesIndexByPosition
                )
            }.toTypedArray()

            upStaircasesIndexByLevel.forEach { (levelIndex, upStaircaseIndex) ->
                val downStaircasePosition = downStaircasesPositionByLevel[levelIndex + 1]!!
                val reachableByDownStaircase =
                    findReacheableEntities(downStaircasePosition, tower.levels(), entitiesIndexByPosition)
                reachableEntities[upStaircaseIndex] = reachableByDownStaircase
            }

            return PlayableTower(
                positionedEntities.size,
                positionedEntities,
                entitiesIndexByPosition,
                startingPosition,
                reachableEntities,
            )
        }

        private fun findReacheableEntities(
            entityPosition: Position,
            data: Array<Level>,
            entitiesIndexByPosition: MutableMap<Position, Int>,
        ): Array<Int> {
            var positionsToCheck = mutableSetOf<Position>()
            val exploredPositions = mutableSetOf<Position>()
            positionsToCheck.add(entityPosition)
            exploredPositions.add(entityPosition)
            val reachableEntities = mutableSetOf<Int>()
            while (positionsToCheck.isNotEmpty()) {
                val newPositionsToCheck = mutableSetOf<Position>()
                for (positionToCheck in positionsToCheck) {
                    if (positionToCheck.column > 0) {
                        checkPosition(
                            Position(positionToCheck.level, positionToCheck.line, positionToCheck.column - 1),
                            reachableEntities,
                            newPositionsToCheck,
                            exploredPositions,
                            entitiesIndexByPosition,
                            data,
                        )
                    }
                    if (positionToCheck.column < (data[positionToCheck.level].columns - 1)) {
                        checkPosition(
                            Position(positionToCheck.level, positionToCheck.line, positionToCheck.column + 1),
                            reachableEntities,
                            newPositionsToCheck,
                            exploredPositions,
                            entitiesIndexByPosition,
                            data,
                        )
                    }
                    if (positionToCheck.line > 0) {
                        checkPosition(
                            Position(positionToCheck.level, positionToCheck.line - 1, positionToCheck.column),
                            reachableEntities,
                            newPositionsToCheck,
                            exploredPositions,
                            entitiesIndexByPosition,
                            data,
                        )
                    }
                    if (positionToCheck.line < (data[positionToCheck.level].lines - 1)) {
                        checkPosition(
                            Position(positionToCheck.level, positionToCheck.line + 1, positionToCheck.column),
                            reachableEntities,
                            newPositionsToCheck,
                            exploredPositions,
                            entitiesIndexByPosition,
                            data,
                        )
                    }
                }
                positionsToCheck = newPositionsToCheck
            }
            return reachableEntities.sorted().toTypedArray()
        }

        private fun checkPosition(
            position: Position,
            reachableEntities: MutableSet<Int>,
            positionsToCheck: MutableSet<Position>,
            exploredPositions: MutableSet<Position>,
            entitiesIndexByPosition: MutableMap<Position, Int>,
            data: Array<Level>,
        ) {
            if (exploredPositions.add(position)) {
                val entity = data[position.level].entities[position.line][position.column]
                if (entity == null) {
                    positionsToCheck.add(position)
                } else if (
                    !(
                            // Can't reach walls or down staircase
                            (entity == Wall.instance) ||
                                    ((entity.getType() == Entity.EntityType.Staircase) && ((entity as Staircase).direction == Staircase.StaircaseDirection.down)))
                ) {
                    val entityIndex = entitiesIndexByPosition[position]!!
                    reachableEntities.add(entityIndex)
                }
            }
        }
    }

}
