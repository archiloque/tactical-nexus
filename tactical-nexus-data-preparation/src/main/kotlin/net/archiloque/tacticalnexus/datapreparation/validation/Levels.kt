package net.archiloque.tacticalnexus.datapreparation.validation;

import net.archiloque.tacticalnexus.datapreparation.EnemyId
import net.archiloque.tacticalnexus.datapreparation.input.entities.Enemy
import net.archiloque.tacticalnexus.datapreparation.input.level.Level

class Levels {

    companion object {
        fun validate(
            levels: List<Level>,
            itemsIdentifiers: List<String>,
            enemies: List<Enemy>,
            statsIds: List<Int>,
        ) {
            println("Validating levels")
            val towerList = levels.map { it.levelCustomFields.tower }.toSet().sorted()
            for (tower in towerList) {
                val levelsIndexForTower =
                    levels.filter { it.levelCustomFields.tower == tower }.map { it.levelCustomFields.level }.sorted()
                var theoricalIndex = 1
                for (levelIndex in levelsIndexForTower) {
                    if (levelIndex != theoricalIndex) {
                        throw RuntimeException("Error in levels list ${levelsIndexForTower.joinToString(", ")}")
                    }
                    theoricalIndex++
                }
                if (!statsIds.contains(tower)) {
                    throw RuntimeException("Stats not found for tower [${tower}]")
                }
            }

            levels.forEach { level ->
                val enemiesIds =
                    enemies.filter { it.tower == level.levelCustomFields.tower }.map { EnemyId.fromEnemy(it) }
                level.entities.enemy?.forEach { enemy ->
                    val enemyId = EnemyId(enemy.type(), enemy.level())
                    if (!enemiesIds.contains(enemyId)) {
                        throw RuntimeException("Unknown enemy [${enemyId}] in level [${level.identifier}] at (${enemy.x}, ${enemy.y})")
                    }
                }
                level.entities.item?.forEach { item ->
                    val itemIdentifier = item.identifier()
                    if (!itemsIdentifiers.contains(itemIdentifier)) {
                        throw RuntimeException("Unknown item [${itemIdentifier}] in level [${level.identifier}] at (${item.x}, ${item.y})")
                    }
                }
            }
        }
    }
}
