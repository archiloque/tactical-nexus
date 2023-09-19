package net.archiloque.tacticalnexus.solver.entities.input

class Exit private constructor() : InputEntity() {
    companion object {
        val instance = Exit()
    }

    override fun getType(): InputEntityType {
        return InputEntityType.Exit
    }
}
