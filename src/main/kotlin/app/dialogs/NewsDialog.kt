package app.dialogs

import app.Navigator
import domain.models.show
import domain.util.MenuState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend fun newsDialog(newsFakeViewModel: NewsFakeViewModel, navigatorController: Navigator) {
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
                    newsFakeViewModel.getAllNews().show()
                }
            }

            "2" -> {
                println("Write keywords separated by spaces:")
                launch(Dispatchers.IO) {
                    newsFakeViewModel.getNewsByKeyword(readln().lowercase().trim().split(' ')).show()
                }
            }

            "exit" -> {
                navigatorController.navigate(MenuState.Exit)
                return@coroutineScope
            }

            else -> println("Incorrect input. Try again.")
        }
        navigatorController.navigate(MenuState.DownloadScreen)
    }
}