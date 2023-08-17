package net.archiloque.tacticalnexus.solver.entities

import net.archiloque.tacticalnexus.solver.code.StateSaver
import net.archiloque.tacticalnexus.solver.code.PlayableTower
import net.archiloque.tacticalnexus.solver.database.State

class Wall() : Entity() {
    companion object {
        val instance = Wall()
    }

    override fun getType(): EntityType {
        return EntityType.Wall
    }

    override fun play(entityIndex: Int, state: State, playableTower: PlayableTower, stateSaver: StateSaver) {
        throw IllegalStateException("Should not happen")
    }
}