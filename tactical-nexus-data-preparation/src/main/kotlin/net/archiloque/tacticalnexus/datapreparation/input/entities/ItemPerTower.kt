package net.archiloque.tacticalnexus.datapreparation.input.entities

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader

class ItemPerTower(
    val tower: Int,
    identifier: String,
    name: String,

    atk: Int,
    def: Int,
    expBonusAdd: Int,
    expBonusMul: Int,
    hp: Int,
    hpBonusAdd: Int,
    hpBonusMul: Int,
): ItemDefault(identifier, name, atk, def, expBonusAdd, expBonusMul, hp, hpBonusAdd, hpBonusMul) {
    companion object {
        fun parse(filePath: String): List<ItemPerTower> {
            println("Reading items per tower at [${filePath}]")
            var result: List<ItemPerTower> = mutableListOf()
            csvReader { delimiter = ';' }.open(filePath) {
                result = readAllWithHeaderAsSequence().map {
                    ItemPerTower(
                        it["Tower"]!!.toInt(),
                        it["Identifier"]!!,
                        it["Name"]!!,
                        it["ATK"]!!.toInt(),
                        it["DEF"]!!.toInt(),
                        it["EXP Bonus Add"]!!.toInt(),
                        it["EXP Bonus Mul"]!!.toInt(),
                        it["HP"]!!.toInt(),
                        it["HP Bonus Add"]!!.toInt(),
                        it["HP Bonus Mul"]!!.toInt(),

                    )
                }.toList()
            }
            return result
        }
    }
}