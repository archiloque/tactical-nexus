package net.archiloque.tacticalnexus.solver.entities.play

import net.archiloque.tacticalnexus.solver.code.StateManager
import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.entities.KeyOrDoorColor
import net.archiloque.tacticalnexus.solver.entities.Position

class GoodiesGroup(
    entityIndex: Int,
    private val items: Array<PositionedItem>,
    private val keys: Array<PositionedKey>,
) : PlayEntity(entityIndex) {

    val item = Item(
        items.sumOf { it.inputItem.atk },
        items.sumOf { it.inputItem.def },
        items.sumOf { it.inputItem.expBonusAdd },
        items.map { it.inputItem.expBonusMul }.fold(1) { acc, i -> acc * i },
        items.sumOf { it.inputItem.hp },
        items.sumOf { it.inputItem.hpBonusAdd },
        items.map { it.inputItem.hpBonusMul }.fold(1) { acc, i -> acc * i },
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

    override fun getType(): PlayEntityType {
        return PlayEntityType.GoodiesGroup
    }

    override fun getPositions(): Array<Position> {
        return positions
    }

    override fun description(): Array<PositionedDescription> {
        return (
                items.map { PositionedDescription("Grab the ${it.inputItem.name.lowercase()}", it.position) } +
                        keys.map { PositionedDescription("Grab the ${it.color.humanName} key", it.position) }
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
        state.greenBlueKeys = (state.greenBlueKeys + greenBlueKeys).toShort()
        state.platinumKeys = (state.platinumKeys + platinumKeys).toShort()
        state.violetKeys = (state.violetKeys + violetKeys).toShort()
        state.yellowKeys = (state.yellowKeys + yellowKeys).toShort()
    }

    override fun toString(): String {
        return "Goodies group: ${(items.map { it.toString() } + keys.map { it.toString() }).joinToString(", ")} with index $entityIndex"
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