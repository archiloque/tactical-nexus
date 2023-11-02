package net.archiloque.tacticalnexus.datapreparation.validation

import net.archiloque.tacticalnexus.datapreparation.input.entities.Item

class Items {

    companion object {
        fun validate(items: List<Item>, itemsIdentifiers: List<String>) {
            println("Validating items")
            Validator.checkDuplicates(itemsIdentifiers.groupBy { it })
            items.forEach {
                if (it.atk < 0) {
                    throw RuntimeException("Bad atk [${it}]")
                } else if (it.def < 0) {
                    throw RuntimeException("Bad def [${it}]")
                } else if (it.expBonus < 0) {
                    throw RuntimeException("Bad exp bonus [${it}]")
                } else if (it.hp < 0) {
                    throw RuntimeException("Bad hp [${it}]")
                } else if (it.hpBonus < 0) {
                    throw RuntimeException("Bad hp bonus [${it}]")
                }
            }
            println("${items.size} items are OK")
        }
    }

}
