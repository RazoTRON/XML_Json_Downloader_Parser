package data

import domain.services.Https
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.*
import java.net.URL

class HttpsImpl : Https {
    override fun downloadFile(url: URL, outputFile: File, bufferSize: Int): Boolean {
        println("Start downloading: ${url.path.substringAfterLast('/')} file.")
        lateinit var inputStream: InputStream
        lateinit var outputStream: OutputStream

        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute()

        if (outputFile.exists()) {
            println("File ${url.path.substringAfterLast('/')} is already downloaded.")
            return true
        }


        (1..4000000000).forEach {
            it.hashCode()
        }

        return try {
            if (response.isSuccessful) {
                inputStream = response.body!!.byteStream()
                outputStream = FileOutputStream(outputFile)

                val buffer = ByteArray(50)
                var readSize = inputStream.read(buffer)

                while (readSize != -1) {
                    outputStream.write(buffer, 0, readSize)
                    readSize = inputStream.read(buffer)
                }
                true
            } else {
                false
            }
        } catch (e: IOException) {
            println(e.localizedMessage)
            false
        } finally {
            try {
                inputStream.close()
                outputStream.close()
                response.close()
            } catch (e: IOException) {
                println("Closing fails: ${e.localizedMessage}")
            }
        }
    }
}