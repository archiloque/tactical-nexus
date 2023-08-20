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
        state.atk = newValue(state.atk, atk, atkType)
        state.def += newValue(state.def, def, defType)
        state.hp += newValue(state.hp, hp, hpType)
    }

    private fun newValue(initialValue: Int, valueToAdd: Int, valueType: ItemPropertyType): Int {
        return if (valueToAdd == 0) {
            initialValue
        } else {
            when (valueType) {
                ItemPropertyType.Points -> {
                    initialValue + valueToAdd
                }

                ItemPropertyType.Percents -> {
                    initialValue * (1 + valueToAdd)
                }
            }
        }
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