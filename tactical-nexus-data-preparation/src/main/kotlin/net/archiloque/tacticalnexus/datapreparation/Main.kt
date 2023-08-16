package net.archiloque.tacticalnexus.datapreparation

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Collectors
import kotlin.io.path.readText
import kotlinx.serialization.json.Json
import net.archiloque.tacticalnexus.datapreparation.input.entities.EnemySheet
import net.archiloque.tacticalnexus.datapreparation.input.entities.Entities
import net.archiloque.tacticalnexus.datapreparation.input.entities.ItemSheet
import net.archiloque.tacticalnexus.datapreparation.input.level.Level
import net.archiloque.tacticalnexus.datapreparation.output.solver.Solver
import net.archiloque.tacticalnexus.datapreparation.validation.Enemies
import net.archiloque.tacticalnexus.datapreparation.validation.Items
import net.archiloque.tacticalnexus.datapreparation.validation.Levels

private val json = Json { ignoreUnknownKeys = true }

data class EnemyId(
    val type: EnemyType,
    val level: Int,
)

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
    val enemiesIds = enemies.map { EnemyId(it.type, it.level) }
    val items = (entities.sheets.find { it.name == "Item" } as? ItemSheet)!!.items
    val itemsIdentifiers = items.map { it.identifier }

    Items.validate(items, itemsIdentifiers)
    Enemies.validate(enemies, enemiesIds, itemsIdentifiers)
    Levels.validate(levels, itemsIdentifiers, enemiesIds)

    Solver(enemies, items, levels).generate()
}
