package net.archiloque.tacticalnexus.solver.code

import net.archiloque.tacticalnexus.solver.database.Position

interface PositionSaver {
    fun save(position: Position)
}