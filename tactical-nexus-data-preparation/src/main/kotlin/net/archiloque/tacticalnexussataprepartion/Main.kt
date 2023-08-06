package net.archiloque.tacticalnexussataprepartion

import kotlinx.serialization.json.Json
import net.archiloque.tacticalnexussataprepartion.formats.input.entities.Entities
import net.archiloque.tacticalnexussataprepartion.formats.input.level.Level
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Collectors
import kotlin.io.path.readText

private val json = Json { ignoreUnknownKeys = true }

fun main(args: Array<String>) {
    println("Looking for levels in [${Paths.get("../tactical-nexus-levels")}]")
    val levelsPathes: List<Path> = Files.walk(Paths.get("../tactical-nexus-levels")).filter {
        Files.isRegularFile(it) && it.fileName.toString() == "data.json"
    }.collect(Collectors.toList())
    val levels = levelsPathes.map {
        println("Processing level at [${it}]")
        val level = json.decodeFromString<Level>(it.readText())
        println(level)
        level
    }
    val entitiesPath = Paths.get("../tactical-nexus-entities.dpo")
    println("Processing entities at [${entitiesPath}]")
    val entities = json.decodeFromString<Entities>(entitiesPath.readText())
    println(entities)

    // Validate there is no duplicated enemies
    // Validate that all enemies are referenced
    // Validate that all items are referenced
}