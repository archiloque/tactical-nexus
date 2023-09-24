package net.archiloque.tacticalnexus.solver.code

import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.entities.play.Enemy
import net.archiloque.tacticalnexus.solver.entities.play.PlayEntityType
import net.archiloque.tacticalnexus.solver.entities.play.PlayableTower

class Player {

    companion object {
        fun play(
            state: State,
            playableTower: PlayableTower,
            stateManager: StateManager,
        ) {
            var reachableEntityIndexFirst = state.reachable.previousSetBit(state.reachable.length() - 1)
            while (reachableEntityIndexFirst >= 0) {
                val positionedEntity = playableTower.playEntities[reachableEntityIndexFirst]
                if ((positionedEntity.getType() == PlayEntityType.Enemy) && ((positionedEntity as Enemy).killNoHPLost(state))) {
                    // If we can kill an enemy without loosing any HP we only try this move
                    positionedEntity.play(reachableEntityIndexFirst, state, playableTower, stateManager)
                    return
                }
                reachableEntityIndexFirst = state.reachable.previousSetBit(reachableEntityIndexFirst - 1)
            }
            var reachableEntityIndexSecond = state.reachable.previousSetBit(state.reachable.length() - 1)
            while (reachableEntityIndexSecond >= 0) {
                val positionedEntity = playableTower.playEntities[reachableEntityIndexSecond]
                positionedEntity.play(reachableEntityIndexSecond, state, playableTower, stateManager)
                reachableEntityIndexSecond = state.reachable.previousSetBit(reachableEntityIndexSecond - 1)
            }
        }

    }
}
