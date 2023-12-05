package net.archiloque.tacticalnexus.datapreparation.input.entities

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader

open class ItemDefault(
    val identifier: String,
    val name: String,

    val atk: Int,
    val def: Int,
    val expBonus: Int,
    val hp: Int,
    val hpBonus: Int,
) {
    companion object {
        fun parse(filePath: String): List<ItemDefault> {
            println("Reading default items at [${filePath}]")
            var result: List<ItemDefault> = mutableListOf()
            csvReader { delimiter = ';' }.open(filePath) {
                result = readAllWithHeaderAsSequence().map {
                    ItemDefault(
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