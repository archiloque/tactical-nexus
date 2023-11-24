package net.archiloque.tacticalnexus.solver.database

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Types
import java.util.BitSet
import org.ktorm.schema.BaseTable
import org.ktorm.schema.Column
import org.ktorm.schema.SqlType

class Mappings {

    object IntArraySqlType : SqlType<IntArray>(Types.ARRAY, "int[]") {

        override fun doSetParameter(ps: PreparedStatement, index: Int, parameter: IntArray) {
            ps.setObject(index, parameter)
        }

        @Suppress("UNCHECKED_CAST")
        override fun doGetResult(rs: ResultSet, index: Int): IntArray? {
            val sqlArray = rs.getArray(index) ?: return null
            try {
                val objectArray = sqlArray.array as Array<Any>?
                return objectArray?.map { it as Int }?.toIntArray()
            } finally {
                sqlArray.free()
            }
        }
    }

    object ShortArraySqlType : SqlType<ShortArray>(Types.ARRAY, "short[]") {

        override fun doSetParameter(ps: PreparedStatement, index: Int, parameter: ShortArray) {
            ps.setObject(index, parameter)
        }

        @Suppress("UNCHECKED_CAST")
        override fun doGetResult(rs: ResultSet, index: Int): ShortArray? {
            val sqlArray = rs.getArray(index) ?: return null
            try {
                val objectArray = sqlArray.array as Array<Any>?
                return objectArray?.map { it as Short }?.toShortArray()
            } finally {
                sqlArray.free()
            }
        }
    }

    object BitSetSqlType : SqlType<BitSet>(Types.BINARY, "bit varying") {

        override fun doSetParameter(ps: PreparedStatement, index: Int, parameter: BitSet) {
            val representation: String?
            representation = (0..<parameter.length()).map { value ->
                if (parameter.get(value)) {
                    '1'
                } else {
                    '0'
                }
            }.joinToString("")
            ps.setObject(index, representation, Types.OTHER)
        }

        override fun doGetResult(rs: ResultSet, index: Int): BitSet {
            val stringValue = rs.getString(index)
            val result = BitSet(stringValue.length)
            stringValue.forEachIndexed { i, c ->
                if (c == '1') {
                    result.set(i, true)
                }
            }
            return result
        }
    }
}

fun BaseTable<*>.intArray(name: String): Column<IntArray> {
    return registerColumn(name, Mappings.IntArraySqlType)
}

fun BaseTable<*>.shortArray(name: String): Column<ShortArray> {
    return registerColumn(name, Mappings.ShortArraySqlType)
}

fun BaseTable<*>.bitSet(name: String): Column<BitSet> {
    return registerColumn(name, Mappings.BitSetSqlType)
}