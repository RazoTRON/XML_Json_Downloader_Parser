package app.dialogs

import app.Navigator
import app.SimulatorViewModel
import domain.models.show
import domain.util.MenuState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend fun newsDialog(simulatorViewModel: SimulatorViewModel) {
    coroutineScope {
        println(
            """
                | Choose one of options:
                | 1. Show all news
                | 2. Show news by keywords
                | Write 'exit' to close program
            """.trimIndent()
        )
        when (readln().lowercase()) {
            "1" -> {
                launch(Dispatchers.IO) {
                    simulatorViewModel.getAllNews().show()
                }
            }

            "2" -> {
                println("Write keywords separated by spaces:")
                launch(Dispatchers.IO) {
                    simulatorViewModel.getNewsByKeyword(readln().lowercase().trim().split(' ')).show()
                }
            }

            "exit" -> {
                Navigator.navigate(MenuState.Exit)
                return@coroutineScope
            }

            else -> println("Incorrect input. Try again.")
        }
        Navigator.navigate(MenuState.DownloadScreen)
    }
}