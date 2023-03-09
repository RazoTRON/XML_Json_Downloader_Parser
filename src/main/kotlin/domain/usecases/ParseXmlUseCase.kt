package domain.usecases

import domain.services.Parser
import domain.services.Repository
import domain.util.ParserHandler
import domain.util.Resource
import kotlinx.coroutines.flow.Flow
import java.io.File

class ParseXmlUseCase(private val parser: Parser, private val repository: Repository) {

    fun <T> execute(file: File, valueType: Class<T>): Flow<Resource<Unit>> =
        ParserHandler().parse(file, valueType, repository, parser::parseXml)
}

