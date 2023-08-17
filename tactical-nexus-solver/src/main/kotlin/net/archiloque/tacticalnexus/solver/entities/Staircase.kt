package net.archiloque.tacticalnexus.solver.entities

import net.archiloque.tacticalnexus.solver.code.PositionSaver
import net.archiloque.tacticalnexus.solver.code.Tower
import net.archiloque.tacticalnexus.solver.database.Position

data class Staircase(val direction: StaircaseDirection) : Entity() {
    companion object {
        val up = Staircase(StaircaseDirection.up)
        val down = Staircase(StaircaseDirection.down)
    }

    override fun getType(): EntityType {
        return EntityType.Staircase
    }

    override fun play(entityIndex: Int, position: Position, tower: Tower, positionSaver: PositionSaver) {
        TODO("Not yet implemented")
    }

    enum class StaircaseDirection() {
        up,
        down
    }
}
