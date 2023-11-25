package net.archiloque.tacticalnexus.datapreparation.validation

import net.archiloque.tacticalnexus.datapreparation.EnemyId
import net.archiloque.tacticalnexus.datapreparation.enums.KeyOrDoorColor
import net.archiloque.tacticalnexus.datapreparation.input.entities.Enemy
import net.archiloque.tacticalnexus.datapreparation.validation.Validator.Companion.checkDuplicates

class Enemies {

    companion object {
        fun validate(enemies: List<Enemy>, itemsIdentifiers: List<String>) {
            println("Validating enemies")
            enemies.forEach {
                if (it.atk <= 0) {
                    throw RuntimeException("Bad atk [${it}]")
                } else if (it.def < 0) {
                    throw RuntimeException("Bad def [${it}]")
                } else if (it.hp <= 0) {
                    throw RuntimeException("Bad hp [${it}]")
                } else if (it.exp <= 0) {
                    throw RuntimeException("Bad exp [${it}]")
                } else if (it.level <= 0) {
                    throw RuntimeException("Bad level [${it}]")
                } else if (it.tower <= 0) {
                    throw RuntimeException("Bad tower  [${it}]")
                } else if (it.drop.isNotEmpty()) {
                    val drop = it.drop
                    if (itemsIdentifiers.contains(drop)) {

                    } else {
                        if (drop.endsWith(Enemy.KEY_SUFFIX) && KeyOrDoorColor.values()
                                .any { it.name.equals(drop.substring(0, drop.length - Enemy.KEY_SUFFIX.length)) }
                        ) {
                        } else {
                            throw RuntimeException("Unknown item [${it}]")
                        }
                    }
                }
            }
            val towers = enemies.map { it.tower }.toSet()
            for (tower in towers) {
                val towerEnemiesIds = enemies.filter { it.tower == tower }.map { EnemyId.fromEnemy(it) }
                checkDuplicates(towerEnemiesIds.groupBy { it })
            }
            println("${enemies.size} enemies are OK")
        }

    }

}
