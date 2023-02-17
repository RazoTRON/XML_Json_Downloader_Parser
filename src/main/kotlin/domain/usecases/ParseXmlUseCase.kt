package domain.usecases

import domain.models.JsonModelDto
import domain.services.Parser
import domain.services.Repository
import domain.util.Resource
import domain.util.parserExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File

class ParseXmlUseCase(private val parser: Parser, private val repository: Repository) {

    fun <T> execute(file: File, valueType: Class<T>): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        var result: T? = null
        parserExceptionHandler {
            result = parser.parseXml(file, valueType)
        }
        if (result != null) {
            emit(Resource.Success())

            when (valueType) {
                JsonModelDto::class.java -> {
                    val jsonModelDto = result as JsonModelDto
                    SaveNewsUseCase(repository).execute(jsonModelDto.newsModels)
                }
            }
        } else {
            emit(Resource.Error())
        }
    }
}

