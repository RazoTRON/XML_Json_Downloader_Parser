package domain.usecases

import domain.services.Https
import java.io.File
import java.net.URL

class DownloadFileUseCase(val https: Https) {
    fun execute(url: URL, outputFile: File, bufferSize: Int = 1024): Boolean {
        return https.downloadFile(url, outputFile, bufferSize)
    }
}