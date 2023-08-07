package net.archiloque.tacticalnexus.datapreparation.output

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import net.archiloque.tacticalnexus.datapreparation.EnemyType
import net.archiloque.tacticalnexus.datapreparation.input.entities.Enemy
import net.archiloque.tacticalnexus.datapreparation.input.entities.Item
import net.archiloque.tacticalnexus.datapreparation.input.level.Level
import java.nio.file.Paths
import javax.annotation.processing.Generated

class Solver(val enemies: List<Enemy>, val items: List<Item>, levels: List<Level>) {
    private val generatedPath = Paths.get("../tactical-nexus-solver/src/main/generated")
    private val arrayOf = MemberName("kotlin", "arrayOf")

    fun generate() {
        generateItems()
        generateEnemies()
    }

    private fun generateItems() {
        val itemClass = ClassName("net.archiloque.tacticalnexus.solver", "Item")

        val itemsCompanion = TypeSpec
            .companionObjectBuilder()
        for (item in items.sortedBy { it.identifier }) {
            val initializerCode = CodeBlock.Builder().add(
                "%T(%S, ${item.atk}, ${item.def}, ${item.hp})",
                itemClass,
                item.identifier
            ).build()
            itemsCompanion.addProperty(
                PropertySpec
                    .builder(item.identifier, itemClass)
                    .initializer(
                        initializerCode
                    )
                    .build()
            )
        }

        val itemsArrayCode = CodeBlock.Builder().add("%M(", arrayOf)
        for (item in items) {
            itemsArrayCode.add(
                "${item.identifier} , ",
            )
        }
        itemsArrayCode.add(")")
        val itemsProperty = PropertySpec
            .builder("items", Array::class.asClassName().parameterizedBy(itemClass))
            .initializer(itemsArrayCode.build())
            .build()

        itemsCompanion.addProperty(
            itemsProperty
        )

        val file = FileSpec
            .builder("net.archiloque.tacticalnexus.solver.input", "Items")
            .addType(
                TypeSpec
                    .classBuilder("Items")
                    .addAnnotation(Generated::class)
                    .addType(
                        itemsCompanion
                            .build()
                    )
                    .build()
            )
        file.build().writeTo(generatedPath)
    }

    private fun generateEnemies() {
        val enemyClass = ClassName("net.archiloque.tacticalnexus.solver", "Enemy")

        val file = FileSpec
            .builder("net.archiloque.tacticalnexus.solver.input", "Enemies")
            .addType(
                TypeSpec
                    .classBuilder("Enemies")
                    .addAnnotation(Generated::class)
                    .addType(
                        TypeSpec
                            .companionObjectBuilder()
                            .addProperty(
                                enemyBuilder(arrayOf, enemyClass, "fighters", EnemyType.fighter)
                            )
                            .addProperty(
                                enemyBuilder(arrayOf, enemyClass, "rangers", EnemyType.ranger)
                            )
                            .build()
                    )
                    .build()
            )
        file.build().writeTo(generatedPath)
    }

    private fun enemyBuilder(
        arrayOf: MemberName,
        enemyClass: ClassName,
        enemyNames: String,
        enemyType: EnemyType,
    ): PropertySpec {
        val enemiesArrayCode = CodeBlock.Builder().add("%M(", arrayOf)
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
            .builder(enemyNames, Array::class.asClassName().parameterizedBy(enemyClass.copy(nullable = true)))
            .initializer(enemiesArrayCode.build())
            .build()
    }

}
