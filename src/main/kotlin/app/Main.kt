package app

import app.dialogs.DownloadFakeViewModel
import app.dialogs.NewsFakeViewModel
import app.dialogs.downloadDialog
import app.dialogs.newsDialog
import data.DownloaderImpl
import data.ParserImpl
import data.RepositoryImpl
import data.Storage
import domain.usecases.*
import domain.util.MenuState
import kotlinx.coroutines.runBlocking

fun main() {

    val navigatorController = Navigator

    val downloader = DownloaderImpl()
    val parser = ParserImpl()
    val repository = RepositoryImpl(Storage)

    val downloadViewModel = DownloadFakeViewModel(
        downloader = DownloadFileUseCase(downloader),
        xmlParser = ParseXmlUseCase(parser, repository),
        jsonParser = ParseJsonUseCase(parser, repository),
    )

    val newsViewModel = NewsFakeViewModel(
        getAllNewsUseCase = GetAllNewsUseCase(repository),
        getNewsByKeywordUseCase = GetNewsByKeywordUseCase(repository),
    )

    runBlocking {
        while (navigatorController.state != MenuState.Exit) {
            when (navigatorController.state) {
                is MenuState.DownloadScreen -> downloadDialog(downloadViewModel, navigatorController)
                is MenuState.NewsScreen -> newsDialog(newsViewModel, navigatorController)
                is MenuState.Exit -> println("Program is closed.")
            }
        }
    }

}