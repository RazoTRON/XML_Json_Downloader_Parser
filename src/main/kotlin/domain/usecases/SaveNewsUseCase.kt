package domain.usecases

import domain.models.NewsModelDto
import domain.services.Repository

class SaveNewsUseCase(private val repository: Repository) {
    fun execute(newsList: List<NewsModelDto>): Boolean {
        val result = repository.saveNews(newsList)
        if (result) println("Is successfully saved to repository.")
        return repository.saveNews(newsList)
    }
}