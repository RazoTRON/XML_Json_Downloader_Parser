package app.dialogs

import app.*
import domain.util.MenuState
import kotlinx.coroutines.*
import java.io.File
import java.net.URL

suspend fun downloadDialog(newsFakeViewModel: DownloadFakeViewModel, navigatorController: Navigator) {

    coroutineScope {

        println(
            """
                | Choose what you want to download and parse:
                | 1. JSON
                | 2. XML
                | Write 'exit' to close program
            """.trimIndent()
        )

        when (readln().lowercase()) {
            "1" -> {
                launch(Dispatchers.IO) {
                    newsFakeViewModel.downloadFile(URL(urlJson), File(jsonOutputFile))
                    newsFakeViewModel.parseJson(File(jsonOutputFile))
                    if (newsFakeViewModel.isDownloadAndParseSuccessful.value) {
                        navigatorController.navigate(MenuState.NewsScreen)
                    }
                }
            }

            "2" -> {
                launch(Dispatchers.IO) {
                    newsFakeViewModel.downloadFile(URL(urlXml), File(xmlOutputFile))
                    newsFakeViewModel.parseXml(File(xmlOutputFile))
                    if (newsFakeViewModel.isDownloadAndParseSuccessful.value) {
                        navigatorController.navigate(MenuState.NewsScreen)
                    }
                }
            }

            "exit" -> {
                navigatorController.navigate(MenuState.Exit)
                return@coroutineScope
            }

            else -> println("Incorrect input. Try again.")
        }

    }
}