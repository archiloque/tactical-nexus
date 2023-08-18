package net.archiloque.tacticalnexus.solver.code

import net.archiloque.tacticalnexus.solver.Position
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
    val stairs: Map<Int, Int>,
) {

    companion object {
        fun prepare(tower: Tower): PlayableTower {
            val positionedEntitiesList = mutableListOf<PositionedEntity>()
            var startingPosition: Int = -1
            val upStaircases = mutableMapOf<Int, Int>()
            val downStaircases = mutableMapOf<Int, Int>()

            tower.levels().forEachIndexed { levelIndex, level ->
                for (line in 0..<level.lines) {
                    for (column in 0..<level.columns) {
                        val entity = level.entities[line][column]
                        if ((entity != null) && (entity.getType() != Entity.EntityType.Wall)) {
                            val positionedEntity = PositionedEntity(entity, levelIndex, line, column)
                            val currentEntityIndex = positionedEntitiesList.size

                            if (entity.getType() == Entity.EntityType.Staircase) {
                                when ((entity as Staircase).direction) {
                                    Staircase.StaircaseDirection.up -> {
                                        upStaircases[levelIndex] = currentEntityIndex
                                    }

                                    Staircase.StaircaseDirection.down -> {
                                        downStaircases[levelIndex] = currentEntityIndex
                                    }
                                }
                            }

                            if (entity.getType() == Entity.EntityType.PlayerStartPosition) {
                                startingPosition = currentEntityIndex
                            }
                            positionedEntitiesList.add(positionedEntity)
                        }
                    }
                }
            }
            val positionedEntities = positionedEntitiesList.toTypedArray()
            val entitiesIndexByPosition = mutableMapOf<Position, Int>()
            positionedEntities.forEachIndexed { index, positionedEntity ->
                entitiesIndexByPosition[Position(
                    positionedEntity.level,
                    positionedEntity.line,
                    positionedEntity.column
                )] = index
            }
            val reachableEntities = positionedEntities.map { positionedEntity ->
                findReacheableEntities(
                    positionedEntity,
                    tower.levels(),
                    entitiesIndexByPosition
                )
            }.toTypedArray()

            val stairs = mutableMapOf<Int, Int>()
            upStaircases.forEach { (levelIndex, entityIndex) ->
                stairs[entityIndex] = downStaircases[levelIndex + 1]!!
            }

            return PlayableTower(
                positionedEntities.size,
                positionedEntities,
                entitiesIndexByPosition,
                startingPosition,
                reachableEntities,
                stairs,
            )
        }

        private fun findReacheableEntities(
            entity: PositionedEntity,
            data: Array<Level>,
            entitiesIndexByPosition: MutableMap<Position, Int>,
        ): Array<Int> {
            var positionsToCheck = mutableSetOf<Position>()
            val exploredPositions = mutableSetOf<Position>()
            positionsToCheck.add(entity.getPosition())
            exploredPositions.add(entity.getPosition())
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
                } else if (entity != Wall.instance) {
                    val entityIndex = entitiesIndexByPosition[position]!!
                    reachableEntities.add(entityIndex)
                }
            }
        }
    }

    data class PositionedEntity(
        val entity: Entity,
        val level: Int,
        val line: Int,
        val column: Int,
    ) {
        fun getPosition(): Position {
            return Position(level, line, column)
        }
    }
}
