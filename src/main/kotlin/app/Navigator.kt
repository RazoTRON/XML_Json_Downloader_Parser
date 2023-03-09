package app

import domain.util.MenuState

object Navigator {
    var state: MenuState = MenuState.DownloadScreen
        private set

    fun navigate(destination: MenuState) {
        state = destination
    }
}