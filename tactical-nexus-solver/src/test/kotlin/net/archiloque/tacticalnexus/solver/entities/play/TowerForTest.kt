package net.archiloque.tacticalnexus.solver.entities.play

import net.archiloque.tacticalnexus.solver.entities.Position
import net.archiloque.tacticalnexus.solver.entities.input.Level
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

    override fun nexusLevels(): Array<TowerLevel> {
        TODO("Not yet implemented")
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

    override fun crownScore(): Position? {
        TODO("Not yet implemented")
    }

    override fun checkScore(): Position {
        TODO("Not yet implemented")
    }

    override fun starScore(): Position {
        TODO("Not yet implemented")
    }

    override fun levels(): Array<Level> {
        TODO("Not yet implemented")
    }

}
