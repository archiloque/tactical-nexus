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
import net.archiloque.tacticalnexus.datapreparation.EnemyType
import net.archiloque.tacticalnexus.datapreparation.input.entities.Stat
import net.archiloque.tacticalnexus.datapreparation.input.level.Door
import net.archiloque.tacticalnexus.datapreparation.input.level.Enemy
import net.archiloque.tacticalnexus.datapreparation.input.level.Entity
import net.archiloque.tacticalnexus.datapreparation.input.level.Exit
import net.archiloque.tacticalnexus.datapreparation.input.level.Item
import net.archiloque.tacticalnexus.datapreparation.input.level.Key
import net.archiloque.tacticalnexus.datapreparation.input.level.Level
import net.archiloque.tacticalnexus.datapreparation.input.level.PlayerStartPosition
import net.archiloque.tacticalnexus.datapreparation.input.level.Staircase
import net.archiloque.tacticalnexus.datapreparation.input.level.Wall

class Levels {
    companion object {
        val doorClass = ClassName(Solver.ENTITIES_PACKAGE, "Door")
        val enemyClass = ClassName(Solver.ENTITIES_PACKAGE, "Enemy")
        val exitClass = ClassName(Solver.ENTITIES_PACKAGE, "Exit")
        val itemsClass = ClassName(Solver.INPUT_PACKAGE, "Items")
        val keyClass = ClassName(Solver.ENTITIES_PACKAGE, "Key")
        val keyOrDoorColorClass = ClassName(Solver.ENTITIES_PACKAGE, "KeyOrDoorColor")
        val levelClass = ClassName(Solver.ENTITIES_PACKAGE, "Level")
        val playerStartPositionClass = ClassName(Solver.ENTITIES_PACKAGE, "PlayerStartPosition")
        val staircaseClass = ClassName(Solver.ENTITIES_PACKAGE, "Staircase")
        val wallClass = ClassName(Solver.ENTITIES_PACKAGE, "Wall")
        val towerInterfaceClass = ClassName(Solver.ENTITIES_PACKAGE, "Tower")

        fun generate(
            levels: List<Level>,
            items: List<net.archiloque.tacticalnexus.datapreparation.input.entities.Item>,
            enemies: List<net.archiloque.tacticalnexus.datapreparation.input.entities.Enemy>,
            stats: List<Stat>,
            generatedPath: Path,
        ) {
            println("Generating levels")


            val towersList = levels.map { it.levelCustomFields.tower }.toSet().sorted()

            for (tower in towersList) {
                println("Generating levels for tower ${tower}")

                val towerStat = stats.find { it.tower == tower }!!

                val className = "Tower_${tower}"

                val towerSpec = TypeSpec
                    .classBuilder(className)
                    .addAnnotation(Generated::class)
                    .addSuperinterface(towerInterfaceClass)
                    .addProperty(
                        enemyBuilder(enemies, items, enemyClass, EnemyType.fighter)
                    )
                    .addProperty(
                        enemyBuilder(enemies, items, enemyClass, EnemyType.ranger)
                    )


                val levelsIndexForTower =
                    levels.filter { it.levelCustomFields.tower == tower }.map { it.levelCustomFields.level }.sorted()

                towerSpec.addProperty(createLevels(levelsIndexForTower, levels, tower))

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

                towerSpec.addProperty(createEnemies(enemies))

                addStatFunction(towerSpec, "atk", towerStat.atk)
                addStatFunction(towerSpec, "def", towerStat.def)
                addStatFunction(towerSpec, "hp", towerStat.hp)

                val file = FileSpec
                    .builder("${Solver.INPUT_PACKAGE}.towers", className)
                    .addType(
                        towerSpec
                            .build()
                    )
                file.build().writeTo(generatedPath)
            }
        }

        private fun createLevels(
            levelsIndexForTower: List<Int>,
            levels: List<Level>,
            tower: Int,
        ): PropertySpec {
            val levelsArrayCode = CodeBlock.Builder().add("%M(", Solver.arrayOf)
            for (level in levelsIndexForTower) {
                val level =
                    levels.find { (it.levelCustomFields.tower == tower) && (it.levelCustomFields.level == level) }!!
                val entities = mutableListOf<Entity>()
                addEntities(level.entities.door, entities)
                addEntities(level.entities.enemy, entities)
                addEntities(level.entities.exit, entities)
                addEntities(level.entities.item, entities)
                addEntities(level.entities.key, entities)
                addEntities(level.entities.staircase, entities)
                addEntities(level.entities.playerStartPosition, entities)
                addEntities(level.entities.wall, entities)
                appendLevelCode(levelsArrayCode, entities)
            }
            levelsArrayCode.add(")")

            val levelsProperty = PropertySpec
                .builder(
                    "levels",
                    Solver.arrayOf(levelClass),
                    KModifier.PRIVATE
                )
                .initializer(levelsArrayCode.build())
                .build()
            return levelsProperty
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

        private fun appendLevelCode(levelsArrayCode: CodeBlock.Builder, entities: List<Entity>) {
            val maxX = entities.maxBy { it.x }.x
            val maxY = entities.maxBy { it.y }.y
            val maxLine = maxY / 16
            val maxColumn = maxX / 16

            val entitiesByPosition = mutableMapOf<Position, Entity>()
            for (entity in entities) {
                entitiesByPosition[Position(entity.y / 16, entity.x / 16)] = entity
            }

            levelsArrayCode.add("Level(${maxLine + 1}, ${maxColumn + 1}, %M(", Solver.arrayOf)
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

                            is Exit -> {
                                levelsArrayCode.add("%T.instance", exitClass)
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

        private fun createEnemies(enemies: List<net.archiloque.tacticalnexus.datapreparation.input.entities.Enemy>): PropertySpec {
            val result = PropertySpec.builder("enemies", Solver.arrayOf(Solver.arrayOf(enemyClass)))
            val enemiesArrayCode = CodeBlock.Builder().add("%M(", Solver.arrayOf)
            enemiesArrayCode.add(")")
            result.initializer(enemiesArrayCode.build())
            return result.build()
        }

        private fun enemyBuilder(
            enemies: List<net.archiloque.tacticalnexus.datapreparation.input.entities.Enemy>,
            items: List<net.archiloque.tacticalnexus.datapreparation.input.entities.Item>,
            enemyClass: ClassName,
            enemyType: EnemyType,
        ): PropertySpec {
            val enemiesArrayCode = CodeBlock.Builder().add("%M(", Solver.arrayOf)
            val maxEnemyLevel = enemies.filter { it.type == enemyType }.maxOf { it.level }
            for (level in 0..maxEnemyLevel) {
                val enemy = enemies.find { (it.type == enemyType) && (it.level == level) }
                if (enemy == null) {
                    enemiesArrayCode.add("null, ")
                } else {
                    val itemIndex = items.indexOfFirst { it.identifier == enemy.drop }
                    enemiesArrayCode.add(
                        "%T(${enemy.hp}, ${enemy.atk}, ${enemy.def}, ${enemy.exp}, Items.${enemy.drop}), ",
                        enemyClass
                    )
                }
            }
            enemiesArrayCode.add(")")
            return PropertySpec
                .builder("${enemyType.name}s", Solver.arrayOf(enemyClass.copy(nullable = true)))
                .addModifiers(KModifier.PRIVATE)
                .initializer(enemiesArrayCode.build())
                .build()
        }

        private data class Position(
            val line: Int,
            val column: Int,
        )

    }

}
