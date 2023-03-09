package domain.usecases

import domain.services.Downloader
import domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.Response
import java.io.File
import java.io.IOException
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.net.URL

class DownloadFileUseCase(private val downloader: Downloader) {
    fun execute(url: URL, outputFile: File, bufferSize: Int = 1024): Flow<Resource<File>> = flow {
        if (outputFile.exists()) {
            emit(Resource.Success(message = "File '${outputFile.name}' is already downloaded."))
            return@flow
        }

        emit(Resource.Loading("${url.path.substringAfterLast('.').uppercase()} file download started."))
        var response: Response? = null
        try {
            response = downloader.request(url)
        } catch (e: IllegalArgumentException) {
            emit(Resource.Error("URL is incorrect. ${e.localizedMessage}"))
        } catch (e: IOException) {
            emit(Resource.Error("Internet connection problem. ${e.localizedMessage}"))
        } catch (e: IllegalStateException) {
            emit(Resource.Error("Request is already executed. ${e.localizedMessage}"))
        }

        if (response == null) {
            emit(Resource.Error("Unexpected error occurs."))
            return@flow
        }

        if (response.isSuccessful) {
            try {
                val file = downloader.saveToFile(response, outputFile, bufferSize)
                emit(Resource.Success(file, "${url.path.substringAfterLast('.').uppercase()} file download is finished."))
            } catch (e: IOException) {
                emit(Resource.Error("Reading/writing file problem occurs. ${e.localizedMessage}"))
            } catch (e: NullPointerException) {
                emit(Resource.Error("Buffer is null. ${e.localizedMessage}"))
            }
        } else {
            emit(Resource.Error())
        }

    }
}