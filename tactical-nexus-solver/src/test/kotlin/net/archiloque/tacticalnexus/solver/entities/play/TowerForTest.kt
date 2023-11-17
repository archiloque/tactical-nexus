package net.archiloque.tacticalnexus.solver.entities.play

import net.archiloque.tacticalnexus.solver.entities.Position
import net.archiloque.tacticalnexus.solver.entities.input.Tower
import net.archiloque.tacticalnexus.solver.entities.input.TowerLevel

class TowerForTest(
    val towerLevels: Array<TowerLevel>,
    val atk: Int,
    val def: Int,
    val hp: Int,
) : Tower {
    override fun standardLevels(): Array<TowerLevel> {
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

    override fun checkScore(): Position {
        return Position(0, 0, 0)
    }

    override fun starScore(): Position {
        return Position(0, 0, 0)
    }

}
