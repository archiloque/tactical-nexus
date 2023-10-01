package net.archiloque.tacticalnexus.solver.entities.play

import net.archiloque.tacticalnexus.solver.code.StateManager
import net.archiloque.tacticalnexus.solver.database.State

data class UpStaircase(val position: Position) : PlayEntitySinglePosition(position) {

    override fun getType(): PlayEntityType {
        return PlayEntityType.UpStaircase
    }

    override fun description(): Array<PositionedDescription> {
        return emptyArray()
    }

    override fun toString(): String {
        return "Up staircase at $position"
    }

    override fun play(
        entityIndex: Int,
        state: State,
        playableTower: PlayableTower,
        stateManager: StateManager,
    ) {
        val newState = newState(entityIndex, state)
        addNewReachablePositions(
            entityIndex,
            newState,
            playableTower
        )
        stateManager.save(newState)
    }

}
