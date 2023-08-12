package net.archiloque.tacticalnexus.solver.entities

data class Door(val color: KeyOrDoorColor) : Entity, KeyOrDoor {

    override fun getType(): Entity.EntityType {
        return Entity.EntityType.Door
    }

    override fun color(): KeyOrDoorColor {
        return color
    }

}

