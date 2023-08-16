package net.archiloque.tacticalnexus.datapreparation.output.solver

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import java.nio.file.Path
import javax.annotation.processing.Generated
import net.archiloque.tacticalnexus.datapreparation.input.level.Door
import net.archiloque.tacticalnexus.datapreparation.input.level.Enemy
import net.archiloque.tacticalnexus.datapreparation.input.level.Entity
import net.archiloque.tacticalnexus.datapreparation.input.level.Item
import net.archiloque.tacticalnexus.datapreparation.input.level.Key
import net.archiloque.tacticalnexus.datapreparation.input.level.Level
import net.archiloque.tacticalnexus.datapreparation.input.level.PlayerStartPosition
import net.archiloque.tacticalnexus.datapreparation.input.level.Staircase
import net.archiloque.tacticalnexus.datapreparation.input.level.Wall

class Levels {
    companion object {
        fun generate(levels: List<Level>, generatedPath: Path) {
            println("Generating levels")
            val doorClass = ClassName(Solver.ENTITIES_PACKAGE, "Door")
            val enemiesClass = ClassName(Solver.INPUT_PACKAGE, "Enemies")
            val itemsClass = ClassName(Solver.INPUT_PACKAGE, "Items")
            val keyClass = ClassName(Solver.ENTITIES_PACKAGE, "Key")
            val keyOrDoorColorClass = ClassName(Solver.ENTITIES_PACKAGE, "KeyOrDoorColor")
            val levelClass = ClassName(Solver.ENTITIES_PACKAGE, "Level")
            val playerStartPositionClass = ClassName(Solver.ENTITIES_PACKAGE, "PlayerStartPosition")
            val staircaseClass = ClassName(Solver.ENTITIES_PACKAGE, "Staircase")
            val wallClass = ClassName(Solver.ENTITIES_PACKAGE, "Wall")

            val towersList = levels.map { it.levelCustomFields.tower }.toSet().sorted()

            for (tower in towersList) {
                println("Generating levels for tower ${tower}")
                val className = "Tower_${tower}"
                val levelsIndexForTower =
                    levels.filter { it.levelCustomFields.tower == tower }.map { it.levelCustomFields.level }.sorted()

                val towerCompanion = TypeSpec
                    .companionObjectBuilder()

                val levelsArrayCode = CodeBlock.Builder().add("%M(", Solver.arrayOf)
                for (level in levelsIndexForTower) {
                    val level =
                        levels.find { (it.levelCustomFields.tower == tower) && (it.levelCustomFields.level == level) }!!
                    val entities = mutableListOf<Entity>()
                    addEntities(level.entities.door, entities)
                    addEntities(level.entities.enemy, entities)
                    addEntities(level.entities.item, entities)
                    addEntities(level.entities.key, entities)
                    addEntities(level.entities.staircase, entities)
                    addEntities(level.entities.playerStartPosition, entities)
                    addEntities(level.entities.wall, entities)

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
                                            "%T.${currentEntity.enemyCustomFields.type}s[${currentEntity.enemyCustomFields.level}]",
                                            enemiesClass
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

                                    is Staircase -> {
                                        levelsArrayCode.add(
                                            "%T(%T.StaircaseDirection.${currentEntity.staircaseCustomFields.direction})",
                                            staircaseClass,
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

                levelsArrayCode.add(")")

                val levelsProperty = PropertySpec
                    .builder(
                        "levels",
                        Array::class.asClassName()
                            .parameterizedBy(
                                levelClass
                            )
                    )
                    .initializer(levelsArrayCode.build())
                    .build()

                towerCompanion.addProperty(
                    levelsProperty
                )

                val file = FileSpec
                    .builder("${Solver.INPUT_PACKAGE}.towers", className)
                    .addType(
                        TypeSpec
                            .classBuilder(className)
                            .addAnnotation(Generated::class)
                            .addType(
                                towerCompanion
                                    .build()
                            )
                            .build()
                    )
                file.build().writeTo(generatedPath)
            }
        }

        private fun addEntities(entities: List<Entity>?, targetList: MutableList<Entity>) {
            if (entities != null) {
                for (entity in entities) {
                    targetList.addAll(entities)
                }
            }
        }

        private data class Position(
            val line: Int,
            val column: Int,
        )

    }

}
