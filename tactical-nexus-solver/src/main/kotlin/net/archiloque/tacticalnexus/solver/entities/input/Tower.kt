package net.archiloque.tacticalnexus.solver.entities.input

import net.archiloque.tacticalnexus.solver.entities.Position

interface Tower {
    fun standardLevels(): Array<TowerLevel>
    fun nexusLevels(): Array<TowerLevel>

    fun atk(): Int
    fun def(): Int
    fun hp(): Int

    fun crownScore(): Position?
    fun checkScore(): Position?
    fun starScore(): Position?

    fun levels(): Array<Level>
}