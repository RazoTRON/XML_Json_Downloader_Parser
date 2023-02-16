package app

import data.HttpsImpl
import domain.usecases.DownloadFileUseCase
import domain.models.JsonModelDto
import domain.usecases.ParseJsonUseCase
import domain.usecases.ParseXmlUseCase
import kotlinx.coroutines.*
import java.io.File
import java.net.URL
import kotlin.system.measureTimeMillis

fun main() {

    val urlJson = URL("https://api2.kiparo.com/static/it_news.json")
    val urlXml = URL("https://api2.kiparo.com/static/it_news.xml")

    val jsonOutputFile = File("it_news.json")
    val xmlOutputFile = File("it_news.xml")

    val downloader = DownloadFileUseCase(HttpsImpl())

    val time = measureTimeMillis {
    runBlocking {

            val isJsonDownloaded = async(Dispatchers.IO) { downloader.execute(urlJson, jsonOutputFile) }
            val isXmlDownloaded = async(Dispatchers.IO) { downloader.execute(urlXml, xmlOutputFile) }

            val parseJsonFile = async(Dispatchers.IO) {
                if (isJsonDownloaded.await()) {
                    ParseJsonUseCase().execute(jsonOutputFile, JsonModelDto::class.java)
                }
            }
            val parseXmlFile = async(Dispatchers.IO) {
                if (isXmlDownloaded.await()) {
                    ParseXmlUseCase().execute(xmlOutputFile, JsonModelDto::class.java)
                }
            }
            val parseJsonUrl = async(Dispatchers.IO) {
                ParseJsonUseCase().execute(urlJson, JsonModelDto::class.java)
            }
            val parseXmlUrl = async(Dispatchers.IO) {
                ParseXmlUseCase().execute(urlXml, JsonModelDto::class.java)
            }
        }
    }
    println(time)
}