package domain.services

import java.io.File
import java.net.URL

interface Downloader {
    fun downloadFile(url: URL, outputFile: File, bufferSize: Int = 1024): Boolean
}