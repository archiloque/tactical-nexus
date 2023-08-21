package net.archiloque.tacticalnexus.solver.code

import net.archiloque.tacticalnexus.solver.database.State

class Player {

    companion object {
        fun play(state: State, playableTower: PlayableTower, stateManager: StateManager) {
            var reachableEntityIndex = state.reachable.previousSetBit(state.reachable.length() - 1)
            while (reachableEntityIndex >= 0) {
                play(reachableEntityIndex, state, playableTower, stateManager)
                reachableEntityIndex = state.reachable.previousSetBit(reachableEntityIndex - 1)
            }
        }

        private fun play(entityIndex: Int, state: State, playableTower: PlayableTower, stateManager: StateManager) {
            val positionedEntity = playableTower.positionedEntities[entityIndex]
            val entity = positionedEntity.entity
            // println(positionedEntity)
            entity.play(entityIndex, state, playableTower, stateManager)
        }
    }
}
