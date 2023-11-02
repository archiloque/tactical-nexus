package net.archiloque.tacticalnexus.solver.entities.play

import kotlin.test.Test
import kotlin.test.assertEquals
import net.archiloque.tacticalnexus.solver.entities.input.Item
import net.archiloque.tacticalnexus.solver.input.Items

class ItemGroupTest {

    private fun createGroup(vararg items: Item): ItemGroup {
        return ItemGroup(items.map { PositionedItem(it, Position(0, 0, 0)) }.toTypedArray())
    }

    @Test
    fun calculation() {
        assertEquals(createGroup(Items.blue_potion, Items.blue_potion).hp, 1600)
        assertEquals(createGroup(Items.power_piece, Items.power_piece).atk, 2)
        assertEquals(createGroup(Items.guard_piece, Items.guard_piece).def, 2)
        assertEquals(createGroup(Items.golden_feather, Items.golden_feather).expBonus, 60)
        assertEquals(createGroup(Items.life_crown, Items.life_crown).hpBonus, 60)

        assertEquals(createGroup(Items.blue_potion, Items.power_piece).hp, 800)
        assertEquals(createGroup(Items.power_piece, Items.red_potion).atk, 1)

    }
}