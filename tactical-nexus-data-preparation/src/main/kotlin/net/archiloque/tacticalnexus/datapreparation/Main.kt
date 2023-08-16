package net.archiloque.tacticalnexus.datapreparation

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Collectors
import kotlin.io.path.readText
import kotlinx.serialization.json.Json
import net.archiloque.tacticalnexus.datapreparation.input.entities.Enemy
import net.archiloque.tacticalnexus.datapreparation.input.entities.EnemySheet
import net.archiloque.tacticalnexus.datapreparation.input.entities.Entities
import net.archiloque.tacticalnexus.datapreparation.input.entities.ItemSheet
import net.archiloque.tacticalnexus.datapreparation.input.entities.StatSheet
import net.archiloque.tacticalnexus.datapreparation.input.level.Level
import net.archiloque.tacticalnexus.datapreparation.output.solver.Solver
import net.archiloque.tacticalnexus.datapreparation.validation.Enemies
import net.archiloque.tacticalnexus.datapreparation.validation.Items
import net.archiloque.tacticalnexus.datapreparation.validation.Levels
import net.archiloque.tacticalnexus.datapreparation.validation.Stats

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
    val levels = levelsPathes.map {
        println("Processing level at [${it}]")
        val level = json.decodeFromString<Level>(it.readText())
        level
    }
    val entitiesPath = Paths.get("../tactical-nexus-entities.dpo")
    println("Processing entities at [${entitiesPath}]")
    val entities = json.decodeFromString<Entities>(entitiesPath.readText())

    val enemies = (entities.sheets.find { it.name == "Enemy" } as? EnemySheet)!!.enemies
    val items = (entities.sheets.find { it.name == "Item" } as? ItemSheet)!!.items
    val itemsIdentifiers = items.map { it.identifier }
    val stats = (entities.sheets.find { it.name == "Stat" } as? StatSheet)!!.stats
    val statsIds = stats.map { it.tower }

    Items.validate(items, itemsIdentifiers)
    Enemies.validate(enemies, itemsIdentifiers)
    Stats.validate(stats)
    Levels.validate(levels, itemsIdentifiers, enemies, statsIds)

    Solver(enemies, items, levels, stats).generate()
}
