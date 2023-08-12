package net.archiloque.tacticalnexus.solver.entities

class Wall() : Entity {
    companion object {
        val instance = Wall()
    }

    override fun getType(): Entity.EntityType {
        return Entity.EntityType.Wall
    }
}