package net.archiloque.tacticalnexus.solver.entities.play

import net.archiloque.tacticalnexus.solver.database.State

open class
Item(
    open val atk: Int,
    open val def: Int,
    open val expBonus: Int,
    open val hp: Int,
    open val hpBonus: Int,
) {

    fun apply(state: State) {
        state.atk += atk
        state.def += def
        state.expBonus = (state.expBonus + expBonus).toShort()
        state.hp += (hp * (100 + state.hpBonus)) / 100
        state.hpBonus = (state.hpBonus + hpBonus).toShort()
    }

}
