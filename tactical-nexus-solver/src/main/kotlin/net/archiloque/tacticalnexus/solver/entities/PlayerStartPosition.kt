package net.archiloque.tacticalnexus.solver.entities

import net.archiloque.tacticalnexus.solver.code.PlayableTower
import net.archiloque.tacticalnexus.solver.code.StateManager
import net.archiloque.tacticalnexus.solver.database.State

class PlayerStartPosition private constructor() : Entity() {

    companion object {
        val instance = PlayerStartPosition()
    }

    override fun getType(): EntityType {
        return EntityType.PlayerStartPosition
    }

    override fun play(
        entityIndex: Int,
        state: State,
        playableTower: PlayableTower,
        stateManager: StateManager,
        newStates: MutableList<State>,
    ) {
        throw IllegalStateException("Should not happen")
    }
}