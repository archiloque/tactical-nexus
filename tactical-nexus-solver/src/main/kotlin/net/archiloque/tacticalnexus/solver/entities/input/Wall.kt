package net.archiloque.tacticalnexus.solver.entities.input

class Wall private constructor() : InputEntity() {
    companion object {
        val instance = Wall()
    }

    override fun getType(): InputEntityType {
        return InputEntityType.Wall
    }
}
