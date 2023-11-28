package net.archiloque.tacticalnexus.datapreparation.validation

import net.archiloque.tacticalnexus.datapreparation.EnemyId
import net.archiloque.tacticalnexus.datapreparation.LEVEL_DIMENSION_PIXELS
import net.archiloque.tacticalnexus.datapreparation.enums.ScoreType
import net.archiloque.tacticalnexus.datapreparation.input.entities.Enemy
import net.archiloque.tacticalnexus.datapreparation.input.level.Score
import net.archiloque.tacticalnexus.datapreparation.input.level.StaircaseDirection
import net.archiloque.tacticalnexus.datapreparation.input.level.TowerLevel

class TowerLevels : Validator() {

    fun validate(
        towerLevels: List<TowerLevel>,
        itemsIdentifiers: List<String>,
        enemies: List<Enemy>,
        statsIds: List<Int>,
    ): Boolean {
        println("Validating levels")
        val towerList = towerLevels.map { it.levelCustomFields.tower }.toSet().sorted()
        for (tower in towerList) {
            val levelsForTower = towerLevels.filter { it.levelCustomFields.tower == tower }
            validateLevelsGroup(statsIds, tower, levelsForTower.filter { it.levelCustomFields.nexus })
            validateLevelsGroup(statsIds, tower, levelsForTower.filter { !it.levelCustomFields.nexus })

            val scores: List<Score> =
                levelsForTower.mapNotNull { towerLevel -> towerLevel.entities.score }.flatten()
            for (scoreType in ScoreType.entries) {
                val scoreNumber = scores.filter { score -> score.score() == scoreType }.size
                if (scoreNumber != 1) {
                    foundError("Found $scoreNumber of score of type $scoreType for tower $tower")
                }
            }
        }

        towerLevels.forEach { level ->
            validateTowerLevel(level, enemies, itemsIdentifiers)
        }
        return hadError
    }

    private fun validateTowerLevel(
        level: TowerLevel,
        enemies: List<Enemy>,
        itemsIdentifiers: List<String>,
    ) {
        if (level.width != LEVEL_DIMENSION_PIXELS) {
            foundError("Level width is ${level.width} while $LEVEL_DIMENSION_PIXELS expected in level [${level.identifier}]")
        }
        if (level.height != LEVEL_DIMENSION_PIXELS) {
            foundError("Level height is ${level.height} while $LEVEL_DIMENSION_PIXELS expected in level [${level.identifier}]")
        }
        val enemiesIds =
            enemies.filter { it.tower == level.levelCustomFields.tower }.map { EnemyId.fromEnemy(it) }
        level.entities.enemy?.forEach { enemy ->
            val enemyId = EnemyId(enemy.type(), enemy.level())
            if (!enemiesIds.contains(enemyId)) {
                foundError("Unknown enemy ${enemyId.type} lv ${enemyId.level} in level [${level.identifier}] at (${enemy.x}, ${enemy.y})")
            }
        }
        level.entities.item?.forEach { item ->
            val itemIdentifier = item.identifier()
            if (!itemsIdentifiers.contains(itemIdentifier)) {
                foundError("Unknown item [${itemIdentifier}] in level [${level.identifier}] at (${item.x}, ${item.y})")
            }
        }
    }

    private fun validateLevelsGroup(
        statsIds: List<Int>,
        tower: Int,
        levelsForTower: List<TowerLevel>,
    ) {
        val levelsIndexForTower = levelsForTower.map { it.levelCustomFields.level }.sorted()
        var theoricalIndex = 1
        for (levelIndex in levelsIndexForTower) {
            if (levelIndex != theoricalIndex) {
                foundError("Error in levels list ${levelsIndexForTower.joinToString(", ")} for tower $tower")
            }
            theoricalIndex++
        }
        if (!statsIds.contains(tower)) {
            foundError("Stats not found for tower [${tower}]")
        }
        if (levelsForTower.size != 1) {
            levelsForTower.forEach { level ->
                val levelLevel = level.levelCustomFields.level

                val downStaircases =
                    level.entities.staircase!!.count { it.staircaseCustomFields.direction == StaircaseDirection.down }
                if (
                    ((levelLevel == 1) && (downStaircases != 0)) ||
                    ((levelLevel != 1) && (downStaircases != 1))
                ) {
                    foundError("Bad number of down stair cases $downStaircases for level $level")
                }

                val upStaircases =
                    level.entities.staircase!!.filter { it.staircaseCustomFields.direction == StaircaseDirection.up }
                        .count()
                if (
                    ((levelLevel == levelsIndexForTower.size) && (upStaircases != 0)) ||
                    ((levelLevel != levelsIndexForTower.size) && (upStaircases != 1))
                ) {
                    foundError("Bad number of up stair cases $upStaircases for level $level")
                }
            }
        }
    }
}
