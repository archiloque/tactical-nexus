package net.archiloque.tacticalnexus.solver.entities

interface Entity {
    fun getType(): EntityType

    enum class EntityType() {
        Door,
        Enemy,
        Item,
        Key,
        PlayerStartPosition,
        Staircase,
        Wall,
    }
}
