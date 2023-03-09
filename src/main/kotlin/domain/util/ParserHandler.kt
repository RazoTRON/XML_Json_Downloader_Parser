package domain.util

import domain.models.JsonModelDto
import domain.services.Repository
import domain.usecases.SaveNewsUseCase
import kotlinx.coroutines.flow.flow
import java.io.File

class ParserHandler {
    fun <T> parse(file: File, valueType: Class<T>, repository: Repository, customParser: (file: File, valueType: Class<T>) -> T?) =
        flow<Resource<Unit>> {
            emit(Resource.Loading())
            var result: T? = null
            parserExceptionHandler {
                result = customParser(file, valueType)
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