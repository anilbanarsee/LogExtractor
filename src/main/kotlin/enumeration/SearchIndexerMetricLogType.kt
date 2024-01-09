package enumeration

enum class SearchIndexerMetricLogType(val regex: Regex) {
    CASE_ID_LIST(Regex("Case ID list retrieved in \\d requests taking (\\d*)ms")),
    ELASTIC_REQUEST(Regex("Elastic request for case ids took (\\d*)ms")),
    GET_SEARCH_HITS(Regex("getSearchHits took (\\d*)ms")),
}
