package net.archiloque.tacticalnexus.solver.entities.play

import net.archiloque.tacticalnexus.solver.database.State

open class
Item(
    open val atk: Int,
    open val def: Int,
    open val expMultAdd: Int,
    open val expMultMul: Int,
    open val hp: Int,
    open val hpMultAdd: Int,
    open val hpMultMul: Int,
) {

    fun apply(state: State) {
        state.atk += atk
        state.def += def
        state.expMult = ((state.expMult + expMultAdd) * expMultMul).toShort()
        state.hp += (hp * state.hpMult) / 100
        state.hpMult = ((state.hpMult + hpMultAdd) * hpMultMul).toShort()
    }

}
