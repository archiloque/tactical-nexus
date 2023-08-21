package net.archiloque.tacticalnexus.solver.code

data class Position(
    val level: Int,
    val line: Int,
    val column: Int,
) {
    fun description(): String {
        return "${level}, ${line}, ${column}"
    }
}
