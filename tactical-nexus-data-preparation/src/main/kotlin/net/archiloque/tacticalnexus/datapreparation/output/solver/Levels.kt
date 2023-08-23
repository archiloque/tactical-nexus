package net.archiloque.tacticalnexus.datapreparation.output.solver

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import java.nio.file.Path
import javax.annotation.processing.Generated
import net.archiloque.tacticalnexus.datapreparation.input.entities.Level

class Levels {
    companion object {
        private val levelClass = ClassName(Solver.ENTITIES_PACKAGE, "Level")

        fun generate(
            levels: List<Level>,
            generatedPath: Path,
        ) {
            println("Generating levels")
            val levelsArrayCode = CodeBlock.Builder().add("%M(", Solver.arrayOf)

            for (level in levels.sortedBy { it.number }) {
                levelsArrayCode.add(
                    "%T(${level.number}, ${level.exp}, ${level.atk}, ${level.def}, ${level.blueKey}, ${level.crimsonKey}, ${level.yellowKey},), ",
                    levelClass,
                )
            }

            levelsArrayCode.add(")")

            val levelsCompanion = TypeSpec
                .companionObjectBuilder()

            val levelsProperty = PropertySpec
                .builder(
                    "levels",
                    Solver.arrayOf(levelClass),
                )
                .initializer(levelsArrayCode.build())
                .build()

            levelsCompanion.addProperty(levelsProperty)

            val file = FileSpec
                .builder(Solver.INPUT_PACKAGE, "Levels")
                .addType(
                    TypeSpec
                        .classBuilder("Levels")
                        .addAnnotation(Generated::class)
                        .addType(
                            levelsCompanion
                                .build()
                        )
                        .build()
                )
            file.build().writeTo(generatedPath)

        }
    }
}