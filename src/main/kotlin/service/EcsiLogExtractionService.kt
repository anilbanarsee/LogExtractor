package service

import enumeration.SearchIndexerMetricLogType
import kotlinx.serialization.json.Json
import model.GrafanaLog
import model.SearchIndexerLog
import model.SearchIndexerMetricLog
import java.io.File
import java.nio.file.Paths

class EcsiLogExtractionService {
    val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    fun extractLogs() {
        val workingDirectory = Paths.get("").toAbsolutePath()

        val logsDirectory = "$workingDirectory/logs_in"
        val outDirectory = "$workingDirectory/extracted_out"

        val extractedLogsMap = File(logsDirectory).walkTopDown()
            .filter { it.isFile }
            .map { it.readText() }
            .flatMap { json.decodeFromString<List<GrafanaLog>>(it) }
            .filter { it.line.contains("{") }
            .map { json.decodeFromString<SearchIndexerLog>(it.line) }
            .mapNotNull { toMetricLog(it.message) }
            .groupBy { it.type }

        extractedLogsMap.forEach { (type, logs) ->
            File("$outDirectory/$type.csv")
                .bufferedWriter()
                .use { out ->
                    logs.forEach {
                        out.write(it.duration.toString())
                        out.newLine()
                    }
                }
        }
    }

    fun toMetricLog(log: String): SearchIndexerMetricLog? {
        return SearchIndexerMetricLogType.values()
            .firstOrNull { it.regex.containsMatchIn(log) }
            ?.let { type ->
                val duration = type.regex.find(log)!!.groupValues[1].toLong()
                return SearchIndexerMetricLog(type, duration)
            }
    }
}
