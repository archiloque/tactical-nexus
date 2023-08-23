package net.archiloque.tacticalnexus.datapreparation.input.entities

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader

data class Item(
    val identifier: String,
    val name: String,

    val atk: Int,
    val def: Int,
    val expBonus: Int,
    val hp: Int,
    val hpBonus: Int,
    ) {
    companion object {
        fun parse(filePath: String): List<Item> {
            println("Reading items at [${filePath}]")
            var result : List<Item> = mutableListOf()
            csvReader{delimiter = ';'}.open(filePath) {
                result = readAllWithHeaderAsSequence().map {
                    Item(
                        it["Identifier"]!!,
                        it["Name"]!!,
                        it["ATK"]!!.toInt(),
                        it["DEF"]!!.toInt(),
                        it["EXP Bonus"]!!.toInt(),
                        it["HP"]!!.toInt(),
                        it["HP Bonus"]!!.toInt(),
                    )
                }.toList()
            }
            return result
        }
    }
}