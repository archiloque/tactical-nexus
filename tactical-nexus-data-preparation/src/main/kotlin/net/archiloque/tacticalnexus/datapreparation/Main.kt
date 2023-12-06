package net.archiloque.tacticalnexus.datapreparation

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Collectors
import kotlin.io.path.readText
import kotlin.system.exitProcess
import kotlinx.serialization.json.Json
import net.archiloque.tacticalnexus.datapreparation.enums.EnemyType
import net.archiloque.tacticalnexus.datapreparation.input.entities.Enemy
import net.archiloque.tacticalnexus.datapreparation.input.entities.ItemDefault
import net.archiloque.tacticalnexus.datapreparation.input.entities.ItemPerTower
import net.archiloque.tacticalnexus.datapreparation.input.entities.Level
import net.archiloque.tacticalnexus.datapreparation.input.entities.Stat
import net.archiloque.tacticalnexus.datapreparation.input.level.TowerLevel
import net.archiloque.tacticalnexus.datapreparation.output.solver.Solver
import net.archiloque.tacticalnexus.datapreparation.validation.Enemies
import net.archiloque.tacticalnexus.datapreparation.validation.ItemsDefault
import net.archiloque.tacticalnexus.datapreparation.validation.ItemsPerTower
import net.archiloque.tacticalnexus.datapreparation.validation.Stats
import net.archiloque.tacticalnexus.datapreparation.validation.TowerLevels

private val json = Json { ignoreUnknownKeys = true }

data class EnemyId(
    val type: EnemyType,
    val level: Int,
) {
    companion object {
        fun fromEnemy(enemy: Enemy): EnemyId {
            return EnemyId(enemy.type, enemy.level)
        }
    }
}

fun main(args: Array<String>) {
    println("Looking for levels in [${Paths.get("../tactical-nexus-levels")}]")
    val levelsPathes: List<Path> = Files.walk(Paths.get("../tactical-nexus-levels")).filter {
        Files.isRegularFile(it) && it.fileName.toString() == "data.json"
    }.collect(Collectors.toList())
    val towerLevels = levelsPathes.map {
        println("Processing tower level at [${it}]")
        val towerLevel = json.decodeFromString<TowerLevel>(it.readText())
        towerLevel
    }
    val enemies = Enemy.parse("../enemies_per_tower.csv")
    val itemsPerTower = ItemPerTower.parse("../items_per_tower.csv")
    val itemsDefault = ItemDefault.parse("../items_default.csv")
    val itemsIdentifiers = itemsDefault.map { it.identifier }
    val stats = Stat.parse("../stats_per_tower.csv")
    val statsIds = stats.map { it.tower }
    val levels = Level.parse("../levels_per_tower.csv")

    val hadError = ItemsDefault().validateDefault(itemsDefault, itemsIdentifiers) ||
            ItemsPerTower().validatePerTower(itemsPerTower, itemsIdentifiers) ||
            Enemies().validate(enemies, itemsIdentifiers) ||
            Stats().validate(stats) ||
            TowerLevels().validate(towerLevels, itemsIdentifiers, enemies, statsIds)
    if (hadError) {
        exitProcess(-1)
    }

    Solver(enemies, itemsDefault, itemsPerTower, towerLevels, stats, levels).generate()
}
