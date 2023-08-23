package net.archiloque.tacticalnexus.datapreparation.validation;

import net.archiloque.tacticalnexus.datapreparation.input.entities.Level
import net.archiloque.tacticalnexus.datapreparation.validation.Validator.Companion.checkDuplicates

class Levels {

    companion object {
        fun validate(stats: List<Level>) {
            println("Validating level")
            stats.forEach {
                if (it.number < 0) {
                    throw RuntimeException("Bad number [${it}]")
                } else if (it.atk < 0) {
                    throw RuntimeException("Bad atk [${it}]")
                } else if (it.def < 0) {
                    throw RuntimeException("Bad def [${it}]")
                } else if (it.blueKey < 0) {
                    throw RuntimeException("Bad blue key [${it}]")
                } else if (it.crimsonKey < 0) {
                    throw RuntimeException("Bad crimson key [${it}]")
                } else if (it.yellowKey < 0) {
                    throw RuntimeException("Bad yellow key [${it}]")
                }
            }
            checkDuplicates(stats.map { it.number }.groupBy { it })
            println("${stats.size} levels are OK")
        }

    }

}
