package net.archiloque.tacticalnexus.solver.entities.play

import net.archiloque.tacticalnexus.solver.code.StateManager
import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.entities.KeyOrDoorColor
import net.archiloque.tacticalnexus.solver.entities.Position

class GoodiesGroup(
    private val entityIndex: Int,
    private val items: Array<PositionedItem>,
    private val keys: Array<PositionedKey>,
) : PlayEntity() {

    val item = Item(
        items.sumOf { it.inputItem.atk },
        items.sumOf { it.inputItem.def },
        items.sumOf { it.inputItem.expBonus },
        items.sumOf { it.inputItem.hp },
        items.sumOf { it.inputItem.hpBonus },
    )

    private val blueKeys = countKeys(KeyOrDoorColor.blue)
    private val crimsonKeys = countKeys(KeyOrDoorColor.crimson)
    private val greenBlueKeys = countKeys(KeyOrDoorColor.greenblue)
    private val platinumKeys = countKeys(KeyOrDoorColor.platinum)
    private val violetKeys = countKeys(KeyOrDoorColor.violet)
    private val yellowKeys = countKeys(KeyOrDoorColor.yellow)

    private fun countKeys(color: KeyOrDoorColor): Short {
        return keys.count { it.color == color }.toShort()
    }

    private val positions = (items.map { it.position } + keys.map { it.position })
        .toTypedArray()

    override fun entityIndex(): Int {
        return entityIndex
    }

    override fun getType(): PlayEntityType {
        return PlayEntityType.GoodiesGroup
    }

    override fun getPositions(): Array<Position> {
        return positions
    }

    override fun description(): Array<PositionedDescription> {
        return (
                items.map { PositionedDescription("Grab the ${it.inputItem.name.lowercase()}", it.position) } +
                        keys.map { PositionedDescription("Grab the ${it.color} key", it.position) }
                ).toTypedArray()
    }

    override fun play(
        state: State,
        playableTower: PlayableTower,
        stateManager: StateManager,
    ) {
        val newState = newState(entityIndex, state)
        apply(newState)
        if (addNewReachablePositions(
                entityIndex,
                newState,
                playableTower,
                stateManager
            )
        ) {
            stateManager.save(newState)
        }
    }

    fun apply(state: State) {
        item.apply(state)
        state.blueKeys = (state.blueKeys + blueKeys).toShort()
        state.crimsonKeys = (state.crimsonKeys + crimsonKeys).toShort()
        state.greenblueKeys = (state.greenblueKeys + greenBlueKeys).toShort()
        state.platinumKeys = (state.platinumKeys + platinumKeys).toShort()
        state.violetKeys = (state.violetKeys + violetKeys).toShort()
        state.yellowKeys = (state.yellowKeys + yellowKeys).toShort()
    }

    override fun toString(): String {
        return "Goodies group: ${(items.map { it.toString() } + keys.map { it.toString() }).joinToString(", ")} and index $entityIndex"
    }
}

data class PositionedItem(
    val inputItem: net.archiloque.tacticalnexus.solver.entities.input.Item,
    val position: Position,
) {
    override fun toString(): String {
        return "${inputItem.name} at $position"
    }
}

data class PositionedKey(
    val color: KeyOrDoorColor,
    val position: Position,
) {
    override fun toString(): String {
        return "${color.name.replaceFirstChar { it.uppercase() }} key at $position"
    }
}