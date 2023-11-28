package net.archiloque.tacticalnexus.datapreparation.validation

import net.archiloque.tacticalnexus.datapreparation.input.entities.Stat

class Stats : Validator() {

    fun validate(stats: List<Stat>): Boolean {
        println("Validating stats")
        stats.forEach {
            if (it.atk < 0) {
                foundError("Bad atk [${it}]")
            } else if (it.def < 0) {
                foundError("Bad def [${it}]")
            } else if (it.hp < 0) {
                foundError("Bad hp [${it}]")
            }
        }
        checkDuplicates(stats.map { it.tower }.groupBy { it })
        return hadError
    }

}
