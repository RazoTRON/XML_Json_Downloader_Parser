package domain.usecases

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import java.io.File
import java.net.URL

class ParseXmlUseCase {
    fun <T> execute(url: URL, valueType: Class<T>): T? {
        println("Start XML URL parsing...")
        var result: T? = null
        parserExceptionHandler {
            result = XmlMapper().readValue(url, valueType)
        }
        println("Finish XML URL parsing.")
        return result
    }

    fun <T> execute(file: File, valueType: Class<T>): T? {
        println("Start XML file parsing...")
        var result: T? = null
        parserExceptionHandler {
            result = XmlMapper().readValue(file, valueType)
        }
        println("Finish XML file parsing.")
        return result
    }
}

