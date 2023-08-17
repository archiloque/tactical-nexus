package net.archiloque.tacticalnexus.solver.entities

import java.util.BitSet
import net.archiloque.tacticalnexus.solver.code.PositionSaver
import net.archiloque.tacticalnexus.solver.code.Tower
import net.archiloque.tacticalnexus.solver.database.Position
import net.archiloque.tacticalnexus.solver.database.PositionStatus

abstract class Entity {
    abstract fun getType(): EntityType

    abstract fun play(entityIndex: Int, position: Position, tower: Tower, positionSaver: PositionSaver)

    enum class EntityType() {
        Door,
        Enemy,
        Item,
        Key,
        PlayerStartPosition,
        Staircase,
        Wall,
    }

    protected fun newPosition(entityIndex: Int, position: Position): Position {
        val visitedEntities = position.visitedEntities.clone() as BitSet
        visitedEntities.set(entityIndex)
        val reachableEntities = position.reachableEntities.clone() as BitSet
        reachableEntities.set(entityIndex, false)
        return Position(
            -1,
            PositionStatus.new,

            visitedEntities,
            reachableEntities,
            position.atk,
            position.def,
            position.hp,

            position.blue_keys,
            position.crimson_keys,
            position.platinum_keys,
            position.violet_keys,
            position.yellow_keys,

            position.moves.plus(entityIndex),
        )
    }


    protected fun addNewPositions(entityIndex: Int, position: Position, tower: Tower, positionSaver: PositionSaver) {
        position.reachableEntities.set(entityIndex, false)
        for (reachableEntity in tower.reachableEntities[entityIndex]) {
            if ((!position.visitedEntities.get(reachableEntity)) && (!position.reachableEntities.get(reachableEntity))) {
                position.reachableEntities.set(reachableEntity)
            }
        }
        positionSaver.save(position)
    }
}
