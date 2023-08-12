package net.archiloque.tacticalnexus.solver.entities

data class Key(val color: KeyOrDoorColor) : Entity, KeyOrDoor {

    override fun getType(): Entity.EntityType {
        return Entity.EntityType.Key
    }

    override fun color(): KeyOrDoorColor {
        return color
    }

}

