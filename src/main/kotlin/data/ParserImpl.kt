package data

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import domain.services.Parser
import java.io.File
import java.net.URL

class ParserImpl : Parser {
    override fun <T> parseJson(file: File, valueType: Class<T>): T? {
        return ObjectMapper().readValue(file, valueType)
    }

    override fun <T> parseJson(url: URL, valueType: Class<T>): T? {
        return ObjectMapper().readValue(url, valueType)
    }

    override fun <T> parseXml(file: File, valueType: Class<T>): T? {
        return XmlMapper().readValue(file, valueType)
    }

    override fun <T> parseXml(url: URL, valueType: Class<T>): T? {
        return XmlMapper().readValue(url, valueType)
    }
}