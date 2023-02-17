package domain.services

import okhttp3.Response
import java.io.File
import java.net.URL

interface Downloader {
    fun request(url: URL): Response
    fun saveToFile(response: Response, outputFile: File, bufferSize: Int = 1024): File
}