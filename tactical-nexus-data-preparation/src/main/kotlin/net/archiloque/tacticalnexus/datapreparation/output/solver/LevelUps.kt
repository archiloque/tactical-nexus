package net.archiloque.tacticalnexus.datapreparation.output.solver

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import java.nio.file.Path
import javax.annotation.processing.Generated
import net.archiloque.tacticalnexus.datapreparation.input.entities.LevelUp

class LevelUps {
    companion object {
        private val levelUpClass = ClassName(Solver.ENTITIES_PACKAGE, "LevelUp")

        fun generate(
            levelUps: List<LevelUp>,
            generatedPath: Path,
        ) {
            println("Generating level ups")
            val levelUpsArrayCode = CodeBlock.Builder().add("%M(", Solver.arrayOf)

            levelUpsArrayCode.add(
                "%T(0, 0, 0, 0, 0, 0, 0,), ",
                levelUpClass,
            )

            for (levelUp in levelUps.sortedBy { it.number }) {
                levelUpsArrayCode.add(
                    "%T(${levelUp.number}, ${levelUp.exp}, ${levelUp.atk}, ${levelUp.def}, ${levelUp.blueKey}, ${levelUp.crimsonKey}, ${levelUp.yellowKey},), ",
                    levelUpClass,
                )
            }

            levelUpsArrayCode.add(")")

            val levelUpsCompanion = TypeSpec
                .companionObjectBuilder()

            val levelUpsProperty = PropertySpec
                .builder(
                    "levelUps",
                    Solver.arrayOf(levelUpClass),
                )
                .initializer(levelUpsArrayCode.build())
                .build()

            levelUpsCompanion.addProperty(levelUpsProperty)

            val file = FileSpec
                .builder(Solver.INPUT_PACKAGE, "LevelUps")
                .addType(
                    TypeSpec
                        .classBuilder("LevelUps")
                        .addAnnotation(Generated::class)
                        .addType(
                            levelUpsCompanion
                                .build()
                        )
                        .build()
                )
            file.build().writeTo(generatedPath)
        }
    }
}