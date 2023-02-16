package domain.usecases

import com.fasterxml.jackson.core.exc.StreamReadException
import com.fasterxml.jackson.databind.DatabindException
import java.io.IOException

fun <T> parserExceptionHandler(body: () -> T) {
    try {
        body()
    } catch (e: IOException) {
        println("Network error. ${e.localizedMessage}")
    } catch (e: StreamReadException) {
        println("Invalid file. ${e.localizedMessage}")
    } catch (e: DatabindException) {
        println("Incorrect file. ${e.localizedMessage}")
    }
}