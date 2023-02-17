package app.dialogs

import app.*
import domain.util.MenuState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.io.File
import java.net.URL


suspend fun downloadDialog(simulatorViewModel: SimulatorViewModel) {
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
                    simulatorViewModel.downloadFile(URL(urlJson), File(jsonOutputFile))
                    simulatorViewModel.parseJson(File(jsonOutputFile))
                }
            }

            "2" -> {
                launch(Dispatchers.IO) {
                    simulatorViewModel.downloadFile(URL(urlXml), File(xmlOutputFile))
                    simulatorViewModel.parseXml(File(xmlOutputFile))
                }
            }

            "exit" -> {
                Navigator.navigate(MenuState.Exit)
                return@coroutineScope
            }

            else -> println("Incorrect input. Try again.")
        }
        Navigator.navigate(MenuState.NewsScreen)
    }
}