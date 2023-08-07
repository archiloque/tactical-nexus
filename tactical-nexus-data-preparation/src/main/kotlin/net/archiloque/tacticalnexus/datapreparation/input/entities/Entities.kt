package net.archiloque.tacticalnexus.datapreparation.input.entities

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
data class Entities(
    @Required val sheets: List<Sheet>,
)
