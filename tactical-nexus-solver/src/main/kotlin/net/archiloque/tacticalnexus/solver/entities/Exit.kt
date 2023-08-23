package net.archiloque.tacticalnexus.solver.entities

import net.archiloque.tacticalnexus.solver.code.PlayableTower
import net.archiloque.tacticalnexus.solver.code.StateManager
import net.archiloque.tacticalnexus.solver.database.State

class Exit private constructor() : Entity() {
    companion object {
        val instance = Exit()
    }

    override fun getType(): EntityType {
        return EntityType.Exit
    }

    override fun play(entityIndex: Int, state: State, playableTower: PlayableTower, stateManager: StateManager) {
        stateManager.reachedExit(state)
    }
}
