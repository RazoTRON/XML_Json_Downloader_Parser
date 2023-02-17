package domain.usecases

import domain.models.NewsModelDto
import domain.services.Repository

class SaveNewsUseCase(private val repository: Repository) {
    fun execute(newsList: List<NewsModelDto>): Boolean {
        println("Is successfully saved to repository.")
        return repository.saveNews(newsList)
    }
}