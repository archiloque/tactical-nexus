package net.archiloque.tacticalnexus.solver.entities

class PlayerStartPosition() : Entity {
    companion object {
        val instance = PlayerStartPosition()
    }

    override fun getType(): Entity.EntityType {
        return Entity.EntityType.PlayerStartPosition
    }
}