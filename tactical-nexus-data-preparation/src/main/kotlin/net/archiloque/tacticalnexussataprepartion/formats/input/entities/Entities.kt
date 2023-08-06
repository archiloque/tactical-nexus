package net.archiloque.tacticalnexussataprepartion.formats.input.entities

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
data class Entities(
    @Required val sheets: List<Sheet>,
)
