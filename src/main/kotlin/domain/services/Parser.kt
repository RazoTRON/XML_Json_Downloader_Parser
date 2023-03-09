package domain.services

import java.io.File
import java.net.URL

interface Parser {
    fun <T> parseJson(file: File, valueType: Class<T>): T?
    fun <T> parseJson(url: URL, valueType: Class<T>): T?
    fun <T> parseXml(file: File, valueType: Class<T>): T?
    fun <T> parseXml(url: URL, valueType: Class<T>): T?
}