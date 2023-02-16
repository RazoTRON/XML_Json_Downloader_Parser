package domain.usecases

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File
import java.net.URL

class ParseJsonUseCase {
    fun <T> execute(url: URL, valueType: Class<T>): T? {
        println("Start Json URL parsing...")
        var result: T? = null
        parserExceptionHandler {
            result = ObjectMapper().readValue(url, valueType)
        }
        println("Finish Json URL parsing.")
        return result
    }

    fun <T> execute(file: File, valueType: Class<T>): T? {
        println("Start Json file parsing...")
        var result: T? = null
        parserExceptionHandler {
            result = ObjectMapper().readValue(file, valueType)
        }
        println("Finish Json file parsing.")
        return result
    }

}