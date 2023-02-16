package domain.usecases

import com.fasterxml.jackson.databind.ObjectMapper
import domain.services.Parser
import java.io.File
import java.net.URL

class ParseJsonUseCase(val parser: Parser) {
    fun <T> execute(url: URL, valueType: Class<T>): T? {
        println("Start Json URL parsing...")
        var result: T? = null
        parserExceptionHandler {
            result = parser.parseJson(url, valueType)
        }
        println("Finish Json URL parsing.")
        return result
    }

    fun <T> execute(file: File, valueType: Class<T>): T? {
        println("Start Json file parsing...")
        var result: T? = null
        parserExceptionHandler {
            result = parser.parseJson(file, valueType)
        }
        println("Finish Json file parsing.")
        return result
    }

}