package net.archiloque.tacticalnexus.solver.entities.input

class Staircase private constructor(val direction: StaircaseDirection) : InputEntity() {
    companion object {
        val up = Staircase(StaircaseDirection.up)
        val down = Staircase(StaircaseDirection.down)
    }

    override fun getType(): InputEntityType {
        return InputEntityType.Staircase
    }

    enum class StaircaseDirection() {
        up,
        down
    }
}
