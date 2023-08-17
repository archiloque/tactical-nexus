package net.archiloque.tacticalnexus.solver.entities

import java.util.BitSet
import net.archiloque.tacticalnexus.solver.code.PlayableTower
import net.archiloque.tacticalnexus.solver.code.StateSaver
import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.database.StateStatus

abstract class Entity {
    abstract fun getType(): EntityType

    abstract fun play(entityIndex: Int, state: State, playableTower: PlayableTower, stateSaver: StateSaver)

    enum class EntityType() {
        Door,
        Enemy,
        Exit,
        Item,
        Key,
        PlayerStartPosition,
        Staircase,
        Wall,
    }

    protected fun newState(entityIndex: Int, state: State): State {
        val visitedEntities = state.visitedEntities.clone() as BitSet
        visitedEntities.set(entityIndex)
        val reachableEntities = state.reachableEntities.clone() as BitSet
        reachableEntities.set(entityIndex, false)
        return State(
            -1, // Ensure it can't be saved
            StateStatus.new,

            visitedEntities,
            reachableEntities,
            state.atk,
            state.def,
            state.hp,

            state.blue_keys,
            state.crimson_keys,
            state.platinum_keys,
            state.violet_keys,
            state.yellow_keys,

            state.moves.plus(entityIndex),
        )
    }


    protected fun addNewPositions(entityIndex: Int, state: State, playableTower: PlayableTower, stateSaver: StateSaver) {
        state.reachableEntities.set(entityIndex, false)
        for (reachableEntity in playableTower.reachableEntities[entityIndex]) {
            if ((!state.visitedEntities.get(reachableEntity)) && (!state.reachableEntities.get(reachableEntity))) {
                state.reachableEntities.set(reachableEntity)
            }
        }
        stateSaver.save(state)
    }
}
