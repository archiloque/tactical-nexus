package net.archiloque.tacticalnexus.solver.entities.input

import net.archiloque.tacticalnexus.solver.entities.Direction

class OneWay(val direction: Direction) : InputEntity() {

    override fun getType(): InputEntityType {
        return InputEntityType.OneWay
    }
}
