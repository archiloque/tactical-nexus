package net.archiloque.tacticalnexus.datapreparation.validation

import net.archiloque.tacticalnexus.datapreparation.EnemyId
import net.archiloque.tacticalnexus.datapreparation.enums.KeyOrDoorColor
import net.archiloque.tacticalnexus.datapreparation.input.entities.Enemy

class Enemies : Validator() {

    fun validate(enemies: List<Enemy>, itemsIdentifiers: List<String>): Boolean {
        println("Validating enemies")
        enemies.forEach {
            if (it.atk <= 0) {
                foundError("Bad atk [${it}], should be >= 0")
            }
            if (it.def < 0) {
                foundError("Bad def [${it}], should be > 0")
            }
            if (it.hp <= 0) {
                foundError("Bad hp [${it}], should be >= 0")
            }
            if (it.exp <= 0) {
                foundError("Bad exp [${it}], should be > 0")
            }
            if (it.level <= 0) {
                foundError("Bad level [${it}], should be > 0")
            }
            if (it.tower <= 0) {
                foundError("Bad tower  [${it}], should be > 0")
            }
            if (it.drop.isNotEmpty()) {
                val drop = it.drop
                if (!itemsIdentifiers.contains(drop)) {
                    if (!drop.endsWith(Enemy.KEY_SUFFIX) || !KeyOrDoorColor.entries
                            .any { it.name == drop.substring(0, drop.length - Enemy.KEY_SUFFIX.length) }
                    ) {
                        foundError("Unknown item [${it}]")
                    }
                }
            }
        }
        val towers = enemies.map { it.tower }.toSet()
        for (tower in towers) {
            val towerEnemiesIds = enemies.filter { it.tower == tower }.map { EnemyId.fromEnemy(it) }
            checkDuplicates(towerEnemiesIds.groupBy { it })
        }
        return hadError
    }
}
