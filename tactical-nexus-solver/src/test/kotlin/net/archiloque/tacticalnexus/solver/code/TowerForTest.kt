package net.archiloque.tacticalnexus.solver.code

import net.archiloque.tacticalnexus.solver.entities.TowerLevel
import net.archiloque.tacticalnexus.solver.entities.Tower

data class TowerForTest(
    val towerLevels: Array<TowerLevel>,
    val atk: Int,
    val def: Int,
    val hp: Int,
) : Tower {
    override fun levels(): Array<TowerLevel> {
        return towerLevels
    }

    override fun atk(): Int {
        return atk
    }

    override fun def(): Int {
        return def
    }

    override fun hp(): Int {
        return hp
    }

}
