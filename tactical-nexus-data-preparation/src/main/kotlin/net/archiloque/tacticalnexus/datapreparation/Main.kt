package net.archiloque.tacticalnexus.datapreparation

import kotlinx.serialization.json.Json
import net.archiloque.tacticalnexus.datapreparation.input.entities.*
import net.archiloque.tacticalnexus.datapreparation.input.level.Level
import net.archiloque.tacticalnexus.datapreparation.output.Solver
import java.lang.RuntimeException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Collectors
import kotlin.io.path.readText

private val json = Json { ignoreUnknownKeys = true }

private data class EnemyId(
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

    validateItems(items, itemsIdentifiers)
    validateEnemies(enemies, enemiesIds, itemsIdentifiers)
    validateLevels(levels, itemsIdentifiers, enemiesIds)

    Solver(enemies, items, levels).generate()
}

private fun validateLevels(levels: List<Level>, itemsIdentifiers: List<String>, enemiesIds: List<EnemyId>) {
    println("Validating levels")
    levels.forEach { level ->
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

private fun validateItems(items: List<Item>, itemsIdentifiers: List<String>) {
    println("Validating items")
    checkDuplicates(itemsIdentifiers.groupBy { it })
    items.forEach {
        if (it.atk < 0) {
            throw RuntimeException("Bad atk [${it}]")
        } else if (it.def < 0) {
            throw RuntimeException("Bad def [${it}]")
        } else if (it.hp < 0) {
            throw RuntimeException("Bad hp [${it}]")
        }
    }
    println("${items.size} items are OK")
}

private fun validateEnemies(enemies: List<Enemy>, enemiesIds: List<EnemyId>, itemsIdentifiers: List<String>) {
    println("Validating enemies")
    enemies.forEach {
        if (it.atk <= 0) {
            throw RuntimeException("Bad atk [${it}]")
        } else if (it.def <= 0) {
            throw RuntimeException("Bad def [${it}]")
        } else if (it.hp <= 0) {
            throw RuntimeException("Bad hp [${it}]")
        } else if (it.exp <= 0) {
            throw RuntimeException("Bad exp [${it}]")
        } else if (it.level <= 0) {
            throw RuntimeException("Bad level [${it}]")
        } else if (!itemsIdentifiers.contains(it.drop)) {
            throw RuntimeException("Unknown item [${it}]")
        }
    }
    checkDuplicates(enemiesIds.groupBy { it })
    println("${enemies.size} enemies are OK")
}

private fun checkDuplicates(elements: Map<Any, List<Any>>) {
    elements.forEach { (t, u)
        ->
        if (u.size > 1) {
            throw RuntimeException("Duplicated element [${t}]")
        }
    }
}
