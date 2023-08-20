package net.archiloque.tacticalnexus.solver.input

import javax.`annotation`.processing.Generated
import net.archiloque.tacticalnexus.solver.entities.Item
import net.archiloque.tacticalnexus.solver.entities.ItemPropertyType

@Generated
public class Items {
    public companion object {
        public val blue_potion: Item = Item(
            "blue_potion", 0, ItemPropertyType.Points, 0,
            ItemPropertyType.Points, 800, ItemPropertyType.Points,
        )

        public val drop_of_dream_ocean: Item = Item(
            "drop_of_dream_ocean", 5, ItemPropertyType.Points,
            5, ItemPropertyType.Points, 9999, ItemPropertyType.Points,
        )

        public val guard_card: Item = Item(
            "guard_card", 0, ItemPropertyType.Points, 5,
            ItemPropertyType.Points, 0, ItemPropertyType.Points,
        )

        public val guard_deck: Item = Item(
            "guard_deck", 0, ItemPropertyType.Points, 15,
            ItemPropertyType.Points, 0, ItemPropertyType.Points,
        )

        public val guard_gem: Item = Item(
            "guard_gem", 0, ItemPropertyType.Points, 2,
            ItemPropertyType.Points, 0, ItemPropertyType.Points,
        )

        public val guard_piece: Item = Item(
            "guard_piece", 0, ItemPropertyType.Points, 1,
            ItemPropertyType.Points, 0, ItemPropertyType.Points,
        )

        public val guard_potion: Item = Item(
            "guard_potion", 0, ItemPropertyType.Points, 3,
            ItemPropertyType.Points, 300, ItemPropertyType.Points,
        )

        public val heavenly_potion: Item = Item(
            "heavenly_potion", 0, ItemPropertyType.Points, 3,
            ItemPropertyType.Points, 3000, ItemPropertyType.Points,
        )

        public val life_crown: Item = Item(
            "life_crown", 0, ItemPropertyType.Points, 0,
            ItemPropertyType.Points, 30, ItemPropertyType.Percents,
        )

        public val life_potion: Item = Item(
            "life_potion", 3, ItemPropertyType.Points, 0,
            ItemPropertyType.Points, 300, ItemPropertyType.Points,
        )

        public val power_card: Item = Item(
            "power_card", 5, ItemPropertyType.Points, 0,
            ItemPropertyType.Points, 0, ItemPropertyType.Points,
        )

        public val power_gem: Item = Item(
            "power_gem", 2, ItemPropertyType.Points, 0,
            ItemPropertyType.Points, 0, ItemPropertyType.Points,
        )

        public val power_piece: Item = Item(
            "power_piece", 1, ItemPropertyType.Points, 0,
            ItemPropertyType.Points, 0, ItemPropertyType.Points,
        )

        public val power_potion: Item = Item(
            "power_potion", 2, ItemPropertyType.Points, 0,
            ItemPropertyType.Points, 0, ItemPropertyType.Points,
        )

        public val red_potion: Item = Item(
            "red_potion", 0, ItemPropertyType.Points, 0,
            ItemPropertyType.Points, 200, ItemPropertyType.Points,
        )
    }
}
