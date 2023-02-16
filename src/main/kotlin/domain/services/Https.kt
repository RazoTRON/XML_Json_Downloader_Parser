package domain.services

import java.io.File
import java.net.URL

interface Https {
    fun downloadFile(url: URL, outputFile: File, bufferSize: Int = 1024): Boolean
}