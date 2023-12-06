package net.archiloque.tacticalnexus.datapreparation.validation

import net.archiloque.tacticalnexus.datapreparation.input.entities.ItemPerTower

class ItemsPerTower : ItemsDefault() {

    fun validatePerTower(items: List<ItemPerTower>, itemsIdentifiers: List<String>): Boolean {
        println("Validating items per tower")
        checkDuplicates(itemsIdentifiers.groupBy { it })
        items.forEach {
            if (it.tower <= 0) {
                foundError("Bad tower  [${it}], should be > 0")
            }
            validate(it)
        }
        return hadError
    }
}
