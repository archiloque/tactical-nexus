package net.archiloque.tacticalnexus.solver.entities.input

class PlayerStartPosition private constructor() : InputEntity() {

    companion object {
        val instance = PlayerStartPosition()
    }

    override fun getType(): InputEntityType {
        return InputEntityType.PlayerStartPosition
    }
}
