package net.archiloque.tacticalnexus.datapreparation.validation

import net.archiloque.tacticalnexus.datapreparation.input.entities.Item

class Items : Validator() {

    fun validate(items: List<Item>, itemsIdentifiers: List<String>): Boolean {
        println("Validating items")
        checkDuplicates(itemsIdentifiers.groupBy { it })
        items.forEach {
            if (it.atk < 0) {
                foundError("Bad atk [${it}]")
            } else if (it.def < 0) {
                foundError("Bad def [${it}]")
            } else if (it.expBonus < 0) {
                foundError("Bad exp bonus [${it}]")
            } else if (it.hp < 0) {
                foundError("Bad hp [${it}]")
            } else if (it.hpBonus < 0) {
                foundError("Bad hp bonus [${it}]")
            }
        }
        return hadError
    }

}
