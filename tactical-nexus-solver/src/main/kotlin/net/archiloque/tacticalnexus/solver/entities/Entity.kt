package net.archiloque.tacticalnexus.solver.entities

import java.util.BitSet
import net.archiloque.tacticalnexus.solver.code.PlayableTower
import net.archiloque.tacticalnexus.solver.code.StateManager
import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.database.StateStatus

abstract class Entity {
    abstract fun getType(): EntityType

    abstract fun play(entityIndex: Int, state: State, playableTower: PlayableTower, stateManager: StateManager)

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
        val visitedEntities = state.visited.clone() as BitSet
        visitedEntities.set(entityIndex)
        val reachableEntities = state.reachable.clone() as BitSet
        reachableEntities.set(entityIndex, false)
        return state.copy(
            id = -1,
            status = StateStatus.new,
            visited = visitedEntities,
            reachable = reachableEntities,
            moves = state.moves.plus(entityIndex)
        )
    }


    protected fun addNewReachablePositions(
        entityIndex: Int,
        state: State,
        playableTower: PlayableTower,
        stateManager: StateManager,
    ) {
        for (reachableEntity in playableTower.reachable[entityIndex]) {
            if ((!state.visited.get(reachableEntity)) && (!state.reachable.get(reachableEntity))) {
                state.reachable.set(reachableEntity)
            }
        }
    }
}
