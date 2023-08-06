package net.archiloque.tacticalnexussataprepartion.formats.input.level

import kotlinx.serialization.Serializable

@Serializable
data class Wall(
    override val x: Int,
    override val y: Int,
) : Entity
