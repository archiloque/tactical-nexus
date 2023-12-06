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
        if (itemDefault.expBonusAdd < 0) {
            foundError("Bad exp add bonus [${itemDefault}]")
        }
        if (itemDefault.expBonusMul <= 0) {
            foundError("Bad exp mul bonus [${itemDefault}]")
        }
        if (itemDefault.hp < 0) {
            foundError("Bad hp [${itemDefault}]")
        }
        if (itemDefault.hpBonusAdd < 0) {
            foundError("Bad hp add bonus [${itemDefault}]")
        }
        if (itemDefault.hpBonusMul <= 0) {
            foundError("Bad hp mul bonus [${itemDefault}]")
        }
    }

}
