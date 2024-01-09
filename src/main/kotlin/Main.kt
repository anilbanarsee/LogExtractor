import service.EcsiLogExtractionService

fun main(args: Array<String>) {
    val ecsiLogExtractionService = EcsiLogExtractionService()

    ecsiLogExtractionService.extractLogs()
}
