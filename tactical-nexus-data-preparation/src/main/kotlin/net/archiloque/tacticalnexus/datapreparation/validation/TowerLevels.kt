package net.archiloque.tacticalnexus.datapreparation.validation

import net.archiloque.tacticalnexus.datapreparation.EnemyId
import net.archiloque.tacticalnexus.datapreparation.enums.ScoreType
import net.archiloque.tacticalnexus.datapreparation.input.entities.Enemy
import net.archiloque.tacticalnexus.datapreparation.input.level.Score
import net.archiloque.tacticalnexus.datapreparation.input.level.StaircaseDirection
import net.archiloque.tacticalnexus.datapreparation.input.level.TowerLevel

class TowerLevels {

    companion object {
        fun validate(
            towerLevels: List<TowerLevel>,
            itemsIdentifiers: List<String>,
            enemies: List<Enemy>,
            statsIds: List<Int>,
        ) {
            println("Validating levels")
            val towerList = towerLevels.map { it.levelCustomFields.tower }.toSet().sorted()
            for (tower in towerList) {
                val levelsForTower = towerLevels.filter { it.levelCustomFields.tower == tower }
                val levelsIndexForTower = levelsForTower.map { it.levelCustomFields.level }.sorted()
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
                levelsForTower.forEach { level ->
                    val levelLevel = level.levelCustomFields.level

                    val downStaircases =
                        level.entities.staircase!!.filter { it.staircaseCustomFields.direction == StaircaseDirection.down }
                            .count()
                    if (
                        ((levelLevel == 1) && (downStaircases != 0)) ||
                        ((levelLevel != 1) && (downStaircases != 1))
                    ) {
                        throw RuntimeException("Bad number of down stair cases $downStaircases for level $level")
                    }

                    val upStaircases =
                        level.entities.staircase!!.filter { it.staircaseCustomFields.direction == StaircaseDirection.up }
                            .count()
                    if (
                        ((levelLevel == levelsIndexForTower.size) && (upStaircases != 0)) ||
                        ((levelLevel != levelsIndexForTower.size) && (upStaircases != 1))
                    ) {
                        throw RuntimeException("Bad number of up stair cases $upStaircases for level $level")
                    }

                    val exits = level.entities.exit
                    if (levelLevel == levelsIndexForTower.size) {
                        if ((exits == null) || (exits.count() != 1)) {
                            throw RuntimeException("Bad number of exists for level $level $exits")
                        }
                    } else {
                        if (exits != null) {
                            throw RuntimeException("Found an exit for level $level $exits")
                        }
                    }
                }

                val scores: List<Score> = towerLevels.mapNotNull { towerLevel -> towerLevel.entities.score }.flatten()
                for(scoreType in ScoreType.entries) {
                    val scoreNumber = scores.filter { score -> score.score() == scoreType }.size
                    if(scoreNumber != 1) {
                        throw RuntimeException("Found $scoreNumber of score of type $scoreType")
                    }
                }
            }

            towerLevels.forEach { level ->
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
