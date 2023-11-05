package net.archiloque.tacticalnexus.solver.code

import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.entities.play.Door
import net.archiloque.tacticalnexus.solver.entities.play.Enemy
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

                if ((positionedEntity.isDoor()) && (playableTower.roomsSingleDoor.indexOf(
                        positionedEntity.entityIndex()
                    ) != -1)
                ) {
                    positionedEntity as Door
                    if (positionedEntity.canApply(state)) {
                        // If there is a door leading to a room with a single entry, we use it
                        positionedEntity.play(state, playableTower, stateManager)
                        return
                    }
                } else if ((positionedEntity.isEnemy()) && ((positionedEntity as Enemy).killNoHPLost(
                        state
                    ))
                ) {
                    // If we can kill an enemy without loosing any HP we only try this move
                    positionedEntity.play(state, playableTower, stateManager)
                    return
                }
                reachableEntityIndexFirst = state.reachable.previousSetBit(reachableEntityIndexFirst - 1)
            }
            var reachableEntityIndexSecond = state.reachable.previousSetBit(state.reachable.length() - 1)
            while (reachableEntityIndexSecond >= 0) {
                val positionedEntity = playableTower.playEntities[reachableEntityIndexSecond]
                positionedEntity.play(state, playableTower, stateManager)
                reachableEntityIndexSecond = state.reachable.previousSetBit(reachableEntityIndexSecond - 1)
            }
        }

    }
}
