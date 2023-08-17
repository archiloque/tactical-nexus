package net.archiloque.tacticalnexus.solver.entities

import net.archiloque.tacticalnexus.solver.code.PositionSaver
import net.archiloque.tacticalnexus.solver.code.Tower
import net.archiloque.tacticalnexus.solver.database.Position

class Wall() : Entity() {
    companion object {
        val instance = Wall()
    }

    override fun getType(): EntityType {
        return EntityType.Wall
    }

    override fun play(entityIndex: Int, position: Position, tower: Tower, positionSaver: PositionSaver) {
        throw IllegalStateException("Should not happen")
    }
}