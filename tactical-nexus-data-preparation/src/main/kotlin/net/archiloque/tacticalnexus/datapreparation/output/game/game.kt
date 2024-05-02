package net.archiloque.tacticalnexus.datapreparation.output.game

import java.io.File
import java.nio.file.Path
import java.nio.file.Paths
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.archiloque.tacticalnexus.datapreparation.input.entities.Enemy
import net.archiloque.tacticalnexus.datapreparation.input.entities.ItemDefault
import net.archiloque.tacticalnexus.datapreparation.input.entities.ItemPerTower
import net.archiloque.tacticalnexus.datapreparation.input.entities.Level
import net.archiloque.tacticalnexus.datapreparation.input.entities.Stat
import net.archiloque.tacticalnexus.datapreparation.input.level.TowerLevel

class Game(
    private val enemies: List<Enemy>,
    private val itemsDefault: List<ItemDefault>,
    private val itemsPerTower: List<ItemPerTower>,
    private val towerLevels: List<TowerLevel>,
    private val stats: List<Stat>,
    private val levels: List<Level>,
) {
    private val generatedPath = Paths.get("../../tactical-fulcrum/src/data")

    @OptIn(ExperimentalSerializationApi::class)
    private val prettyJson = Json { // this returns the JsonBuilder
        prettyPrint = true
        // optional: specify indent
        prettyPrintIndent = " "
    }

    fun generate() {
        println("Generating data for game")
        val towersList = towerLevels.map { it.levelCustomFields.tower }.toSet().sorted()

        for (tower in towersList) {
            generateTower(
                tower,
                itemsPerTower.filter { it.tower == tower },
                stats.find { it.tower == tower }!!,
                enemies.filter { it.tower == tower },
                levels.filter { it.tower == tower },
                towerLevels.filter { it.levelCustomFields.tower == tower },
                generatedPath
            )
        }
    }

    private fun generateTower(
        towerIndex: Int,
        itemsPerTower: List<ItemPerTower>,
        stat: Stat,
        enemies: List<Enemy>,
        levels: List<Level>,
        towerLevels: List<TowerLevel>,
        generatedPath: Path,
    ) {
        val levelsIndexForTower =
            towerLevels.filter { it.levelCustomFields.nexus == true }
                .map { it.levelCustomFields.level }
                .sorted()
        val filteredTowerLevels = mutableListOf<TowerLevel>()
        for (levelIndex in levelsIndexForTower) {
            val level =
                towerLevels.find {
                    val customFields = it.levelCustomFields
                    (customFields.level == levelIndex) && (customFields.nexus == true)
                }!!
            filteredTowerLevels.add(level)
        }

        val tower = Tower(
            enemies.map { net.archiloque.tacticalnexus.datapreparation.output.game.Enemy.fromInput(it) },
            filteredTowerLevels.map { Room.fromInput(it) },
        )
        val filePath = "${generatedPath}/${towerIndex}.json"
        println("Generating [${filePath}]")
        File(filePath).writeText(prettyJson.encodeToString(tower))
    }
}
