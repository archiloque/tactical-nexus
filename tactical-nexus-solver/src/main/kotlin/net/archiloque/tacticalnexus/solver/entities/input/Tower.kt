package net.archiloque.tacticalnexus.solver.entities.input

interface Tower {
    fun levels(): Array<TowerLevel>

    fun atk(): Int
    fun def(): Int
    fun hp(): Int
}