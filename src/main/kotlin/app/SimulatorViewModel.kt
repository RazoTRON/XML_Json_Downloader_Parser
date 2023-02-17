package app

import domain.models.JsonModelDto
import domain.usecases.*
import domain.util.MenuState
import domain.util.Resource
import java.io.File
import java.net.URL

class SimulatorViewModel(
    private val downloader: DownloadFileUseCase,
    private val xmlParser: ParseXmlUseCase,
    private val jsonParser: ParseJsonUseCase,
    private val getAllNewsUseCase: GetAllNewsUseCase,
    private val getNewsByKeywordUseCase: GetNewsByKeywordUseCase
) {

    var menuState: MenuState = MenuState.DownloadScreen
    fun getAllNews() = getAllNewsUseCase.execute(sortByDate = true)
    fun getNewsByKeyword(keywords: List<String>) = getNewsByKeywordUseCase.execute(keywords, sortByDate = true)

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
                    menuState = MenuState.NewsScreen
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
                    menuState = MenuState.NewsScreen
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