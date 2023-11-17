package net.archiloque.tacticalnexus.solver.entities.play

import net.archiloque.tacticalnexus.solver.code.StateManager
import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.entities.Position

data class UpStaircase(val entityIndex: Int, val position: Position) : PlayEntitySinglePosition(entityIndex, position) {

    override fun isUpStaircase(): Boolean {
        return true
    }

    override fun getType(): PlayEntityType {
        return PlayEntityType.UpStaircase
    }

    override fun description(): Array<PositionedDescription> {
        return emptyArray()
    }

    override fun toString(): String {
        return "Up staircase at $position and index $entityIndex"
    }

    override fun play(
        state: State,
        playableTower: PlayableTower,
        stateManager: StateManager,
    ) {
        val newState = newState(entityIndex, state)
        if (addNewReachablePositions(
                entityIndex,
                newState,
                playableTower,
                stateManager
            )
        ) {
            stateManager.save(newState)
        }
    }

}
