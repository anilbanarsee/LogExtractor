package model

import kotlinx.serialization.Serializable

@Serializable
data class GrafanaLog(
    val line: String,
)
