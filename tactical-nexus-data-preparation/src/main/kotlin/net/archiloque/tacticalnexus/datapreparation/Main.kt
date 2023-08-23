package net.archiloque.tacticalnexus.datapreparation

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Collectors
import kotlin.io.path.readText
import kotlinx.serialization.json.Json
import net.archiloque.tacticalnexus.datapreparation.input.entities.Enemy
import net.archiloque.tacticalnexus.datapreparation.input.entities.Item
import net.archiloque.tacticalnexus.datapreparation.input.entities.Level
import net.archiloque.tacticalnexus.datapreparation.input.entities.Stat
import net.archiloque.tacticalnexus.datapreparation.input.level.TowerLevel
import net.archiloque.tacticalnexus.datapreparation.output.solver.Solver
import net.archiloque.tacticalnexus.datapreparation.validation.Enemies
import net.archiloque.tacticalnexus.datapreparation.validation.Items
import net.archiloque.tacticalnexus.datapreparation.validation.Levels
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
    val enemies = Enemy.parse("../enemies.csv")
    val items = Item.parse("../items.csv")
    val itemsIdentifiers = items.map { it.identifier }
    val stats = Stat.parse("../stats.csv")
    val statsIds = stats.map { it.tower }
    val levels = Level.parse("../levels.csv")

    Items.validate(items, itemsIdentifiers)
    Enemies.validate(enemies, itemsIdentifiers)
    Stats.validate(stats)
    Levels.validate(levels)
    TowerLevels.validate(towerLevels, itemsIdentifiers, enemies, statsIds)

    Solver(enemies, items, towerLevels, stats).generate()
}
