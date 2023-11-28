package net.archiloque.tacticalnexus.datapreparation.enums

import kotlinx.serialization.Serializable

@Serializable
enum class Direction() {
    down,
    left,
    right,
    up,
}