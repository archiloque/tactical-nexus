package net.archiloque.tacticalnexus.datapreparation.input.entities

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Required
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("name")
sealed interface Sheet {
    @Required
    val name: String
}
