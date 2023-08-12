package net.archiloque.tacticalnexus.datapreparation.validation;

import net.archiloque.tacticalnexus.datapreparation.EnemyId
import net.archiloque.tacticalnexus.datapreparation.input.entities.Enemy
import net.archiloque.tacticalnexus.datapreparation.validation.Validator.Companion.checkDuplicates

class Enemies {

    companion object {
        fun validate(enemies: List<Enemy>, enemiesIds: List<EnemyId>, itemsIdentifiers: List<String>) {
            println("Validating enemies")
            enemies.forEach {
                if (it.atk <= 0) {
                    throw RuntimeException("Bad atk [${it}]")
                } else if (it.def <= 0) {
                    throw RuntimeException("Bad def [${it}]")
                } else if (it.hp <= 0) {
                    throw RuntimeException("Bad hp [${it}]")
                } else if (it.exp <= 0) {
                    throw RuntimeException("Bad exp [${it}]")
                } else if (it.level <= 0) {
                    throw RuntimeException("Bad level [${it}]")
                } else if (!itemsIdentifiers.contains(it.drop)) {
                    throw RuntimeException("Unknown item [${it}]")
                }
            }
            checkDuplicates(enemiesIds.groupBy { it })
            println("${enemies.size} enemies are OK")
        }

    }

}
