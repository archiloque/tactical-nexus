package net.archiloque.tacticalnexus.datapreparation.input.level

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Level(
    @Required @SerialName("identifier") val identifier: String,
    @Required @SerialName("entities") val entities: Entities,
    @SerialName("customFields") @Required val levelCustomFields: LevelCustomFields,
)

@Serializable
data class LevelCustomFields(
    @Required @SerialName("tower") val tower: Int,
    @Required @SerialName("level") val level: Int,
)
