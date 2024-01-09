package model

import enumeration.SearchIndexerMetricLogType

data class SearchIndexerMetricLog(
    val type: SearchIndexerMetricLogType,
    val duration: Long,
)
