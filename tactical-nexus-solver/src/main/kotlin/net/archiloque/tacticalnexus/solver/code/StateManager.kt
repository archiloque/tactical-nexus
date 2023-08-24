package net.archiloque.tacticalnexus.solver.code

import net.archiloque.tacticalnexus.solver.database.State

interface StateManager {
    fun save(states: List<State>)

    fun reachedExit(state: State)
}