package net.archiloque.tacticalnexus.solver.code

import net.archiloque.tacticalnexus.solver.database.State

class Player {

    companion object {
        fun play(state: State, playableTower: PlayableTower, stateSaver: StateSaver) {
            var reachableEntityIndex = state.reachable.nextSetBit(0)
            while ((reachableEntityIndex >= 0) && (reachableEntityIndex < playableTower.entitiesNumber)) {
                play(reachableEntityIndex, state, playableTower, stateSaver)
                reachableEntityIndex = state.reachable.nextSetBit(reachableEntityIndex + 1)
            }
        }

        private fun play(entityIndex: Int, state: State, playableTower: PlayableTower, stateSaver: StateSaver) {
            val positionedEntity = playableTower.positionedEntities[entityIndex]
            val entity = positionedEntity.entity
            println(positionedEntity)
            entity.play(entityIndex, state, playableTower, stateSaver)
        }
    }
}
