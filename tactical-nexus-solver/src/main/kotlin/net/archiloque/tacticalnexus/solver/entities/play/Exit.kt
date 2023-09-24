package net.archiloque.tacticalnexus.solver.entities.play

import net.archiloque.tacticalnexus.solver.code.StateManager
import net.archiloque.tacticalnexus.solver.database.State

data class Exit(val position: Position) : PlayEntitySinglePosition(position) {

    override fun getType(): PlayEntityType {
        return PlayEntityType.Exit
    }

    override fun description(): Array<PositionedDescription> {
        return arrayOf(PositionedDescription("Take exit", position))
    }

    override fun play(
        entityIndex: Int,
        state: State,
        playableTower: PlayableTower,
        stateManager: StateManager,
    ) {
        stateManager.reachedExit(state)
    }
}
