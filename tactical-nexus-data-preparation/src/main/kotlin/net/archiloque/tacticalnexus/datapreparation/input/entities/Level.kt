package net.archiloque.tacticalnexus.datapreparation.input.entities

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader

data class Level(
    val number: Int,
    val exp: Int,

    val atk: Int,
    val def: Int,
    val blueKey: Int,
    val crimsonKey: Int,
    val yellowKey: Int,
    ) {
    companion object {
        fun parse(filePath: String): List<Level> {
            println("Reading levels at [${filePath}]")
            var result : List<Level> = mutableListOf()
            csvReader{delimiter = ';'}.open(filePath) {
                result = readAllWithHeaderAsSequence().map {
                    Level(
                        it["Number"]!!.toInt(),
                        it["EXP"]!!.toInt(),
                        it["ATK"]!!.toInt(),
                        it["DEF"]!!.toInt(),
                        it["Blue Key"]!!.toInt(),
                        it["Crimson Key"]!!.toInt(),
                        it["Yellow Key"]!!.toInt(),
                    )
                }.toList()
            }
            return result
        }
    }
}