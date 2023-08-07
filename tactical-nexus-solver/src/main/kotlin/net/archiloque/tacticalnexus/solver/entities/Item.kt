package net.archiloque.tacticalnexus.solver.entities

data class Item(
    val name: String,
    val atk: Int,
    val def: Int,
    val hp: Int,
) : Entity