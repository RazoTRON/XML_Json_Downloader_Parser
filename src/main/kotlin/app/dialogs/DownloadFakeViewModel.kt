package app.dialogs

import domain.models.JsonModelDto
import domain.usecases.*
import domain.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.File
import java.net.URL

class DownloadFakeViewModel(
    private val downloader: DownloadFileUseCase,
    private val xmlParser: ParseXmlUseCase,
    private val jsonParser: ParseJsonUseCase,
) {
    val isDownloadAndParseSuccessful = MutableStateFlow(false)

    suspend fun downloadFile(url: URL, outputFile: File) {
        downloader.execute(url, outputFile).collect {
            when (it) {
                is Resource.Success -> {
                    println(it.message)
                }

                is Resource.Loading -> {
                    println(it.message)
                }

                is Resource.Error -> {
                    println("Downloading Json file is fails: ${it.message}.")
                }
            }
        }
    }

    suspend fun parseXml(file: File) {
        xmlParser.execute(file, JsonModelDto::class.java).collect {
            when (it) {
                is Resource.Success -> {
                    println("XML file parsing is finished.")
                    isDownloadAndParseSuccessful.emit(true)
                }

                is Resource.Loading -> {
                    println("XML file parsing is started.")
                }

                is Resource.Error -> {
                    println("XML file parsing is fails: ${it.message}.")
                }
            }
        }
    }

    suspend fun parseJson(file: File) {
        jsonParser.execute(file, JsonModelDto::class.java).collect {
            when (it) {
                is Resource.Success -> {
                    println("JSON file parsing is finished.")
                    isDownloadAndParseSuccessful.emit(true)
                }

                is Resource.Loading -> {
                    println("JSON file parsing is started.")
                }

                is Resource.Error -> {
                    println("JSON file parsing is fails: ${it.message}.")
                }
            }
        }
    }
}