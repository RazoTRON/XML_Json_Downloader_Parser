package domain.util

sealed class MenuState {
    object DownloadScreen : MenuState()
    object NewsScreen : MenuState()
    object Exit : MenuState()
}
