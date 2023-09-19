package net.archiloque.tacticalnexus.solver.entities.input

import net.archiloque.tacticalnexus.solver.entities.KeyOrDoorColor

class Key(val color: KeyOrDoorColor) : InputEntity() {

    override fun getType(): InputEntityType {
        return InputEntityType.Key
    }
}
