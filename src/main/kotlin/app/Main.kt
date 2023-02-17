package app

import app.dialogs.downloadDialog
import app.dialogs.newsDialog
import data.DownloaderImpl
import data.ParserImpl
import data.RepositoryImpl
import data.Storage
import domain.usecases.*
import domain.util.MenuState
import kotlinx.coroutines.*

const val urlJson = "https://api2.kiparo.com/static/it_news.json"
const val urlXml = "https://api2.kiparo.com/static/it_news.xml"

const val jsonOutputFile = "it_news.json"
const val xmlOutputFile = "it_news.xml"

fun main() {

    val vm = SimulatorViewModel(
        downloader = DownloadFileUseCase(DownloaderImpl()),
        xmlParser = ParseXmlUseCase(ParserImpl(), RepositoryImpl(Storage)),
        jsonParser = ParseJsonUseCase(ParserImpl(), RepositoryImpl(Storage)),
        getAllNewsUseCase = GetAllNewsUseCase(RepositoryImpl(Storage)),
        getNewsByKeywordUseCase = GetNewsByKeywordUseCase(RepositoryImpl(Storage)),
    )

    runBlocking {
        while (Navigator.state != MenuState.Exit) {
            when (Navigator.state) {
                is MenuState.DownloadScreen -> downloadDialog(vm)
                is MenuState.NewsScreen -> newsDialog(vm)
                is MenuState.Exit -> println("Program is closed.")
            }
        }
    }
}










