package net.archiloque.tacticalnexus.solver.entities.play

import kotlin.test.Test
import kotlin.test.assertEquals
import net.archiloque.tacticalnexus.solver.entities.Position
import net.archiloque.tacticalnexus.solver.entities.input.Item
import net.archiloque.tacticalnexus.solver.input.Items

class ItemGroupTest {

    private fun createGroup(vararg items: Item): ItemGroup {
        return ItemGroup(0, items.map { PositionedItem(it, Position(0, 0, 0)) }.toTypedArray())
    }

    @Test
    fun calculation() {
        assertEquals(createGroup(Items.blue_potion, Items.blue_potion).item.hp, 1600)
        assertEquals(createGroup(Items.power_piece, Items.power_piece).item.atk, 2)
        assertEquals(createGroup(Items.guard_piece, Items.guard_piece).item.def, 2)
        assertEquals(createGroup(Items.golden_feather, Items.golden_feather).item.expBonus, 60)
        assertEquals(createGroup(Items.life_crown, Items.life_crown).item.hpBonus, 60)

        assertEquals(createGroup(Items.blue_potion, Items.power_piece).item.hp, 800)
        assertEquals(createGroup(Items.power_piece, Items.red_potion).item.atk, 1)

    }
}