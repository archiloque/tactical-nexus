package net.archiloque.tacticalnexus.solver.entities

class Staircase(val direction: StaircaseDirection) : Entity {
    companion object {
        val up = Staircase(StaircaseDirection.up)
        val down = Staircase(StaircaseDirection.down)
    }
}

enum class StaircaseDirection() {
    up,
    down
}
