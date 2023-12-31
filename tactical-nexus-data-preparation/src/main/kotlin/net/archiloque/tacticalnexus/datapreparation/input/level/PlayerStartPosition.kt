package net.archiloque.tacticalnexus.datapreparation.input.level

import kotlinx.serialization.Serializable

@Serializable
data class PlayerStartPosition(
    override val x: Int,
    override val y: Int,
) : Entity
