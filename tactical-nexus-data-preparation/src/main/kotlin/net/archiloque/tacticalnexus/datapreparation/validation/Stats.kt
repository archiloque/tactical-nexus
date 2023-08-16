package net.archiloque.tacticalnexus.datapreparation.validation;

import net.archiloque.tacticalnexus.datapreparation.input.entities.Stat
import net.archiloque.tacticalnexus.datapreparation.validation.Validator.Companion.checkDuplicates

class Stats {

    companion object {
        fun validate(stats: List<Stat>) {
            println("Validating stats")
            stats.forEach {
                if (it.atk < 0) {
                    throw RuntimeException("Bad atk [${it}]")
                } else if (it.def < 0) {
                    throw RuntimeException("Bad def [${it}]")
                } else if (it.hp < 0) {
                    throw RuntimeException("Bad hp [${it}]")
                }
            }
            checkDuplicates(stats.map { it.tower }.groupBy { it })
            println("${stats.size} enemies are OK")
        }

    }

}
