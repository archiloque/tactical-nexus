package net.archiloque.tacticalnexus.solver.code

import net.archiloque.tacticalnexus.solver.database.State

interface StateSaver {
    fun save(state: State)
}