package net.archiloque.tacticalnexus.solver.entities

data class Staircase(val direction: StaircaseDirection) : Entity {
    companion object {
        val up = Staircase(StaircaseDirection.up)
        val down = Staircase(StaircaseDirection.down)
    }

    override fun getType(): Entity.EntityType {
        return Entity.EntityType.Staircase
    }

    enum class StaircaseDirection() {
        up,
        down
    }
}
