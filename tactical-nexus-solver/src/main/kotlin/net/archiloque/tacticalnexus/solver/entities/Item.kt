package net.archiloque.tacticalnexus.solver.entities

import net.archiloque.tacticalnexus.solver.code.PlayableTower
import net.archiloque.tacticalnexus.solver.code.StateSaver
import net.archiloque.tacticalnexus.solver.database.State

data class Item(
    val name: String,
    val atk: Int,
    val atkType: ItemPropertyType,
    val def: Int,
    val defType: ItemPropertyType,
    val hp: Int,
    val hpType: ItemPropertyType,
) : Entity() {
    override fun getType(): EntityType {
        return EntityType.Item
    }

    fun collect(state: State) {
        state.atk += atk
        state.def += def
        state.hp += hp
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