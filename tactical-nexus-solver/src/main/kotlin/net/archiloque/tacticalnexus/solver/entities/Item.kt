package net.archiloque.tacticalnexus.solver.entities

import net.archiloque.tacticalnexus.solver.code.PlayableTower
import net.archiloque.tacticalnexus.solver.code.StateSaver
import net.archiloque.tacticalnexus.solver.database.State

data class Item(
    val name: String,
    val atk: Int,
    val def: Int,
    val expBonus: Int,
    val hp: Int,
    val hpBonus: Int,
) : Entity() {
    override fun getType(): EntityType {
        return EntityType.Item
    }

    fun collect(state: State) {
        state.atk = atk
        state.def += def
        state.expBonus += expBonus
        state.hp += (hp * (100 + hpBonus)) / 100
        state.hpBonus += hpBonus
    }

    override fun play(entityIndex: Int, state: State, playableTower: PlayableTower, stateSaver: StateSaver) {
        val newState = newState(entityIndex, state)
        collect(newState)
        addNewReachablePositions(
            entityIndex,
            newState,
            playableTower,
            stateSaver
        )
        stateSaver.save(newState)
    }
}

enum class ItemPropertyType {
    Points,
    Percents
}