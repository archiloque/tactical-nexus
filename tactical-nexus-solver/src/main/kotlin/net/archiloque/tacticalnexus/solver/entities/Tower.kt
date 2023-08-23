package net.archiloque.tacticalnexus.solver.entities

interface Tower {
    fun levels(): Array<TowerLevel>

    fun atk(): Int
    fun def(): Int
    fun hp(): Int
}