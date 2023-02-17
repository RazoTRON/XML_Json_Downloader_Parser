package data

import domain.services.Downloader
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.*
import java.lang.NullPointerException
import java.net.URL

class DownloaderImpl : Downloader {

    override fun request(url: URL): Response {
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        return client.newCall(request).execute()
    }

    override fun saveToFile(response: Response, outputFile: File, bufferSize: Int): File {
        lateinit var inputStream: InputStream
        lateinit var outputStream: OutputStream

        return try {
            inputStream = response.body!!.byteStream()
            outputStream = FileOutputStream(outputFile)

            val buffer = ByteArray(bufferSize)
            var readSize = inputStream.read(buffer)

            while (readSize != -1) {
                outputStream.write(buffer, 0, readSize)
                readSize = inputStream.read(buffer)
            }
            outputFile
        } catch (e: IOException) {
            throw e
        } catch (e: NullPointerException) {
            throw e
        } finally {
            try {
                inputStream.close()
                outputStream.close()
                response.close()
                println("Closing streams and response is successful.")
            } catch (e: IOException) {
                println("Closing of streams or response fails: ${e.localizedMessage}")
            }
        }
    }


}