package net.archiloque.tacticalnexus.datapreparation.output.solver

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import java.nio.file.Path
import javax.annotation.processing.Generated
import kotlin.system.exitProcess
import net.archiloque.tacticalnexus.datapreparation.enums.EnemyType
import net.archiloque.tacticalnexus.datapreparation.enums.ScoreType
import net.archiloque.tacticalnexus.datapreparation.input.entities.Level
import net.archiloque.tacticalnexus.datapreparation.input.entities.Stat
import net.archiloque.tacticalnexus.datapreparation.input.level.Door
import net.archiloque.tacticalnexus.datapreparation.input.level.Enemy
import net.archiloque.tacticalnexus.datapreparation.input.level.Entity
import net.archiloque.tacticalnexus.datapreparation.input.level.Item
import net.archiloque.tacticalnexus.datapreparation.input.level.Key
import net.archiloque.tacticalnexus.datapreparation.input.level.PlayerStartPosition
import net.archiloque.tacticalnexus.datapreparation.input.level.Score
import net.archiloque.tacticalnexus.datapreparation.input.level.Staircase
import net.archiloque.tacticalnexus.datapreparation.input.level.TowerLevel
import net.archiloque.tacticalnexus.datapreparation.input.level.Wall

class Towers {
    companion object {
        private val doorClass = ClassName(Solver.INPUT_ENTITIES_PACKAGE, "Door")
        private val enemyClass = ClassName(Solver.INPUT_ENTITIES_PACKAGE, "Enemy")
        private val enemyTypeClass = ClassName(Solver.ENTITIES_PACKAGE, "EnemyType")
        private val itemsClass = ClassName(Solver.INPUT_PACKAGE, "Items")
        private val levelClass = ClassName(Solver.INPUT_ENTITIES_PACKAGE, "Level")
        private val keyClass = ClassName(Solver.INPUT_ENTITIES_PACKAGE, "Key")
        private val keyOrDoorColorClass = ClassName(Solver.ENTITIES_PACKAGE, "KeyOrDoorColor")
        private val playerStartPositionClass = ClassName(Solver.INPUT_ENTITIES_PACKAGE, "PlayerStartPosition")
        private val positionClass = ClassName(Solver.ENTITIES_PACKAGE, "Position")
        private val staircaseClass = ClassName(Solver.INPUT_ENTITIES_PACKAGE, "Staircase")
        private val towerLevelClass = ClassName(Solver.INPUT_ENTITIES_PACKAGE, "TowerLevel")
        private val towerInterfaceClass = ClassName(Solver.INPUT_ENTITIES_PACKAGE, "Tower")
        private val wallClass = ClassName(Solver.INPUT_ENTITIES_PACKAGE, "Wall")

        fun generate(
            towerLevels: List<TowerLevel>,
            enemies: List<net.archiloque.tacticalnexus.datapreparation.input.entities.Enemy>,
            stats: List<Stat>,
            levels: List<Level>,
            generatedPath: Path,
        ) {
            println("Generating levels")

            val towersList = towerLevels.map { it.levelCustomFields.tower }.toSet().sorted()

            for (tower in towersList) {
                generateTower(
                    tower,
                    stats.find { it.tower == tower }!!,
                    enemies,
                    levels.filter { it.tower == tower },
                    towerLevels.filter { it.levelCustomFields.tower == tower },
                    generatedPath
                )
            }
        }

        private fun generateTower(
            tower: Int,
            stat: Stat,
            enemies: List<net.archiloque.tacticalnexus.datapreparation.input.entities.Enemy>,
            levels: List<Level>,
            towerLevels: List<TowerLevel>,
            generatedPath: Path,
        ) {
            println("Generating levels for tower $tower")

            val className = "Tower_${tower}"

            val towerSpec = TypeSpec
                .classBuilder(className)
                .addAnnotation(Generated::class)
                .addSuperinterface(towerInterfaceClass)

            for (enemyType in EnemyType.entries) {
                addEnemies(enemies, enemyType, towerSpec)
            }

            addLevels(levels, towerSpec)

            addTowerLevels(towerLevels, tower, false, towerSpec)
            addTowerLevels(towerLevels, tower, true, towerSpec)

            addStatFunction(towerSpec, "atk", stat.atk)
            addStatFunction(towerSpec, "def", stat.def)
            addStatFunction(towerSpec, "hp", stat.hp)

            addScores(towerSpec, towerLevels)

            val file = FileSpec
                .builder("${Solver.INPUT_PACKAGE}.towers", className)
                .addType(
                    towerSpec
                        .build()
                )
            file.build().writeTo(generatedPath)
        }

        private fun addTowerLevels(
            towerLevels: List<TowerLevel>,
            tower: Int,
            nexus: Boolean,
            towerSpec: TypeSpec.Builder,
        ) {
            val levelsArrayCode = CodeBlock.Builder().add("%M(", Solver.arrayOf)

            val levelsIndexForTower =
                towerLevels.filter { it.levelCustomFields.nexus == nexus }
                    .map { it.levelCustomFields.level }
                    .sorted()

            for (levelIndex in levelsIndexForTower) {
                val level =
                    towerLevels.find {
                        val customFields = it.levelCustomFields
                        (customFields.level == levelIndex) && (customFields.nexus == nexus)
                    }!!
                val entities = mutableListOf<Entity>()
                addEntities(level.entities.door, entities)
                addEntities(level.entities.enemy, entities)
                addEntities(level.entities.item, entities)
                addEntities(level.entities.key, entities)
                addEntities(level.entities.staircase, entities)
                addEntities(level.entities.playerStartPosition, entities)
                addEntities(level.entities.wall, entities)

                addTowerLevel(levelsArrayCode, entities)
            }
            levelsArrayCode.add(")")

            val propertyPrefix = if (nexus) {
                "nexus"
            } else {
                "standard"
            }

            towerSpec.addProperty(
                PropertySpec
                    .builder(
                        "${propertyPrefix}Levels",
                        Solver.arrayOf(towerLevelClass),
                        KModifier.PRIVATE
                    )
                    .initializer(levelsArrayCode.build())
                    .build()
            )

            towerSpec.addFunction(
                FunSpec.builder(
                    "${propertyPrefix}Levels",
                )
                    .addModifiers(KModifier.OVERRIDE)
                    .returns(
                        Solver.arrayOf(towerLevelClass)
                    )
                    .addCode("return ${propertyPrefix}Levels")
                    .build()
            )
        }

        private fun addEntities(entities: List<Entity>?, targetList: MutableList<Entity>) {
            if (entities != null) {
                for (entity in entities) {
                    targetList.addAll(entities)
                }
            }
        }

        private fun addStatFunction(towerSpec: TypeSpec.Builder, name: String, value: Int) {
            towerSpec.addFunction(
                FunSpec.builder(
                    name,
                )
                    .addModifiers(KModifier.OVERRIDE)
                    .returns(ClassName("kotlin", "Int"))
                    .addCode("return %L", value)
                    .build()
            )
        }

        private fun addScores(towerSpec: TypeSpec.Builder, levels: List<TowerLevel>) {
            for (scoreType in ScoreType.entries) {
                val level = levels.find { level ->
                    val scores = level.entities.score
                    (scores != null) && scores.any { it.score() == scoreType }
                }
                val score = level!!.entities.score!!.first { score -> score.score() == scoreType }

                towerSpec.addProperty(
                    PropertySpec.builder("${scoreType.name}Score", positionClass)
                        .addModifiers(KModifier.PRIVATE)
                        .initializer(
                            CodeBlock.builder()
                                .add(
                                    "%T(${level.levelCustomFields.level - 1}, ${score.x / 16}, ${score.y / 16},)",
                                    positionClass
                                )
                                .build()
                        )
                        .build()
                )
                towerSpec.addFunction(
                    FunSpec.builder(
                        "${scoreType.name}Score",
                    )
                        .addModifiers(KModifier.OVERRIDE)
                        .returns(positionClass)
                        .addCode(
                            "return ${scoreType.name}Score",
                        )
                        .build()
                )
            }
        }

        private fun addTowerLevel(levelsArrayCode: CodeBlock.Builder, entities: List<Entity>) {
            val maxX = entities.maxBy { it.x }.x
            val maxY = entities.maxBy { it.y }.y
            val maxLine = maxY / 16
            val maxColumn = maxX / 16

            val entitiesByPosition = mutableMapOf<Position, Entity>()
            for (entity in entities) {
                entitiesByPosition[Position(entity.y / 16, entity.x / 16)] = entity
            }

            levelsArrayCode.add("%T(${maxLine + 1}, ${maxColumn + 1}, %M(", towerLevelClass, Solver.arrayOf)
            for (line in 0..maxLine) {
                levelsArrayCode.add("%M(", Solver.arrayOf)
                for (column in 0..maxColumn) {
                    val currentEntity = entitiesByPosition[Position(line, column)]
                    if (currentEntity == null) {
                        levelsArrayCode.add("null")
                    } else {
                        when (currentEntity) {
                            is Door -> {
                                levelsArrayCode.add(
                                    "%T(%T.${currentEntity.keyOrDoorCustomFields.color})",
                                    doorClass,
                                    keyOrDoorColorClass
                                )
                            }

                            is Enemy -> {
                                levelsArrayCode.add(
                                    "${currentEntity.enemyCustomFields.type}s[${currentEntity.enemyCustomFields.level}]"
                                )
                            }

                            is Item -> {
                                levelsArrayCode.add("%T.${currentEntity.itemCustomFields.item}", itemsClass)
                            }

                            is Key -> {
                                levelsArrayCode.add(
                                    "%T(%T.${currentEntity.keyOrDoorCustomFields.color})",
                                    keyClass,
                                    keyOrDoorColorClass
                                )
                            }

                            is PlayerStartPosition -> {
                                levelsArrayCode.add("%T.instance", playerStartPositionClass)
                            }

                            is Score -> {
                                exitProcess(0)
                            }

                            is Staircase -> {
                                levelsArrayCode.add(
                                    "%T.${currentEntity.staircaseCustomFields.direction}",
                                    staircaseClass
                                )
                            }

                            is Wall -> {
                                levelsArrayCode.add("%T.instance", wallClass)
                            }
                        }
                    }
                    levelsArrayCode.add(", ")
                }
                levelsArrayCode.add("), ")
            }
            levelsArrayCode.add(")), ")
        }

        private fun addEnemies(
            enemies: List<net.archiloque.tacticalnexus.datapreparation.input.entities.Enemy>,
            enemyType: EnemyType,
            towerSpec: TypeSpec.Builder,
        ) {
            val enemiesForType = enemies.filter { it.type == enemyType }
            if (enemiesForType.isNotEmpty()) {
                val maxEnemyLevel = enemiesForType.maxOf { it.level }
                val enemiesArrayCode = CodeBlock.Builder().add("%M(\n", Solver.arrayOf)
                for (level in 0..maxEnemyLevel) {
                    val enemy = enemiesForType.find { it.level == level }
                    if (enemy == null) {
                        enemiesArrayCode.add("null,\n")
                    } else {
                        enemiesArrayCode.add(
                            "%T(%T.${enemyType.name}, ${level}, ${enemy.hp}, ${enemy.atk}, ${enemy.def}, ${enemy.exp}, ",
                            enemyClass,
                            enemyTypeClass
                        )

                        if (enemy.drop.isNotEmpty()) {
                            enemiesArrayCode.add("%T.${enemy.drop}", itemsClass)
                        } else {
                            enemiesArrayCode.add("null")
                        }
                        enemiesArrayCode.add("),\n")
                    }
                }
                enemiesArrayCode.add(")")
                towerSpec.addProperty(
                    PropertySpec
                        .builder("${enemyType.name}s", Solver.arrayOf(enemyClass.copy(nullable = true)))
                        .addModifiers(KModifier.PRIVATE)
                        .initializer(enemiesArrayCode.build())
                        .build()
                )
            }
        }

        private fun addLevels(
            levels: List<Level>,
            towerSpec: TypeSpec.Builder,
        ) {
            val levelsArrayCode = CodeBlock.Builder().add("%M(\n", Solver.arrayOf)
            for (level in levels) {
                levelsArrayCode.add(
                    "%T(${level.atkAdd}, ${level.atkMul}, ${level.defAdd}, ${level.defMul}, ${level.hpAdd}, ${level.hpMul}, ${level.blueKey}, ${level.crimsonKey}, ${level.yellowKey}),\n",
                    levelClass,
                )
            }
            levelsArrayCode.add(")")
            towerSpec.addProperty(
                PropertySpec
                    .builder("levels", Solver.arrayOf(levelClass.copy()))
                    .addModifiers(KModifier.PRIVATE)
                    .initializer(levelsArrayCode.build())
                    .build()
            )

            towerSpec.addFunction(
                FunSpec.builder(
                    "levels",
                )
                    .addModifiers(KModifier.OVERRIDE)
                    .returns(
                        Solver.arrayOf(levelClass)
                    )
                    .addCode("return levels")
                    .build()
            )
        }

        private data class Position(
            val line: Int,
            val column: Int,
        )

    }

}
