package net.archiloque.tacticalnexus.solver.code

import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.entities.Enemy
import net.archiloque.tacticalnexus.solver.entities.Entity

class Player {

    companion object {
        fun play(
            state: State,
            playableTower: PlayableTower,
            stateManager: StateManager,
            newStates: MutableList<State>,
        ) {
            var reachableEntityIndexFirst = state.reachable.previousSetBit(state.reachable.length() - 1)
            var oneShotEnemyFound = false
            while (reachableEntityIndexFirst >= 0) {
                val positionedEntity = playableTower.positionedEntities[reachableEntityIndexFirst]
                val entity = positionedEntity.entity
                if((entity.getType() == Entity.EntityType.Enemy) && ((entity as Enemy).killNoHPLost(state))) {
                    oneShotEnemyFound = true
                    entity.play(reachableEntityIndexFirst, state, playableTower, stateManager, newStates)
                }
                reachableEntityIndexFirst = state.reachable.previousSetBit(reachableEntityIndexFirst - 1)
            }
            if(! oneShotEnemyFound) {
                var reachableEntityIndexSecond = state.reachable.previousSetBit(state.reachable.length() - 1)
                while (reachableEntityIndexSecond >= 0) {
                    val positionedEntity = playableTower.positionedEntities[reachableEntityIndexSecond]
                    val entity = positionedEntity.entity
                    entity.play(reachableEntityIndexSecond, state, playableTower, stateManager, newStates)
                    reachableEntityIndexSecond = state.reachable.previousSetBit(reachableEntityIndexSecond - 1)
                }
            }
        }

    }
}
