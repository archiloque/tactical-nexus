package net.archiloque.tacticalnexus.solver.entities.input

import net.archiloque.tacticalnexus.solver.entities.Position

interface Tower {
    fun levels(): Array<TowerLevel>

    fun atk(): Int
    fun def(): Int
    fun hp(): Int

    fun checkScore(): Position
    fun starScore(): Position
}