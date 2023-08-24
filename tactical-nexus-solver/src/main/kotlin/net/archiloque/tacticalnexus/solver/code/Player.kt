package net.archiloque.tacticalnexus.solver.code

import net.archiloque.tacticalnexus.solver.database.State

class Player {

    companion object {
        fun play(
            state: State,
            playableTower: PlayableTower,
            stateManager: StateManager,
            newStates: MutableList<State>,
        ) {
            var reachableEntityIndex = state.reachable.previousSetBit(state.reachable.length() - 1)
            while (reachableEntityIndex >= 0) {
                play(reachableEntityIndex, state, playableTower, stateManager, newStates)
                reachableEntityIndex = state.reachable.previousSetBit(reachableEntityIndex - 1)
            }
        }

        private fun play(
            entityIndex: Int,
            state: State,
            playableTower: PlayableTower,
            stateManager: StateManager,
            newStates: MutableList<State>,
        ) {
            val positionedEntity = playableTower.positionedEntities[entityIndex]
            val entity = positionedEntity.entity
            // println(positionedEntity)
            entity.play(entityIndex, state, playableTower, stateManager, newStates)
        }
    }
}
