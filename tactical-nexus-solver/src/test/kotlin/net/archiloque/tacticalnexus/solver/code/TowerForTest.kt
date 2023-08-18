package net.archiloque.tacticalnexus.solver.code

import net.archiloque.tacticalnexus.solver.entities.Level
import net.archiloque.tacticalnexus.solver.entities.Tower

data class TowerForTest(
    val levels: Array<Level>,
    val atk: Int,
    val def: Int,
    val hp: Int,
) : Tower {
    override fun levels(): Array<Level> {
        return levels
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
