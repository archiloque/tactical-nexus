package net.archiloque.tacticalnexus.datapreparation.validation

import net.archiloque.tacticalnexus.datapreparation.input.entities.ItemDefault

open class ItemsDefault : Validator() {

    fun validateDefault(items: List<ItemDefault>, itemsIdentifiers: List<String>): Boolean {
        println("Validating default items")
        checkDuplicates(itemsIdentifiers.groupBy { it })
        items.forEach {
            validate(it)
        }
        return hadError
    }

    fun validate(itemDefault: ItemDefault) {
        if (itemDefault.atk < 0) {
            foundError("Bad atk [${itemDefault}]")
        }
        if (itemDefault.def < 0) {
            foundError("Bad def [${itemDefault}]")
        }
        if (itemDefault.expMultAdd < 0) {
            foundError("Bad exp mult add bonus [${itemDefault}]")
        }
        if (itemDefault.expMultMul <= 0) {
            foundError("Bad exp mult mul bonus [${itemDefault}]")
        }
        if (itemDefault.hp < 0) {
            foundError("Bad hp [${itemDefault}]")
        }
        if (itemDefault.hpMultAdd < 0) {
            foundError("Bad hp mult add bonus [${itemDefault}]")
        }
        if (itemDefault.hpMultMul <= 0) {
            foundError("Bad hp mult mul bonus [${itemDefault}]")
        }
    }

}
