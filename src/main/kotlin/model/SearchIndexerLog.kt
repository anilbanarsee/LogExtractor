package model

import kotlinx.serialization.Serializable

@Serializable
data class SearchIndexerLog(
    val message: String,
)
