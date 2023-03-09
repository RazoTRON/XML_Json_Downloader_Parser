package domain.usecases

import domain.models.NewsModelDto
import domain.services.Repository

class GetAllNewsUseCase(private val repository: Repository) {
    fun execute(sortByDate: Boolean): List<NewsModelDto> {
        val newsList = repository.getNews()
        return if (sortByDate) {
            newsList.sortedBy { it.date }
        } else {
            newsList
        }
    }
}