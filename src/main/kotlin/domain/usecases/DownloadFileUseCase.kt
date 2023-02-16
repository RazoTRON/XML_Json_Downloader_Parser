package domain.usecases

import domain.services.Downloader
import java.io.File
import java.net.URL

class DownloadFileUseCase(val downloader: Downloader) {
    fun execute(url: URL, outputFile: File, bufferSize: Int = 1024): Boolean {
        return downloader.downloadFile(url, outputFile, bufferSize)
    }
}