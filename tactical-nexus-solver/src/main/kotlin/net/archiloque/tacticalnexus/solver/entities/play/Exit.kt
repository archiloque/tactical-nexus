package net.archiloque.tacticalnexus.solver.entities.play

import net.archiloque.tacticalnexus.solver.code.StateManager
import net.archiloque.tacticalnexus.solver.database.State

data class Exit(val entityIndex: Int, val position: Position) : PlayEntitySinglePosition(entityIndex, position) {

    override fun getType(): PlayEntityType {
        return PlayEntityType.Exit
    }

    override fun description(): Array<PositionedDescription> {
        return arrayOf(PositionedDescription("Take exit", position))
    }

    override fun play(
        state: State,
        playableTower: PlayableTower,
        stateManager: StateManager,
    ) {
        stateManager.reachedExit(state.moves)
    }
}
