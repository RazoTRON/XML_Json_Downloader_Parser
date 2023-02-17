package domain.usecases

import domain.models.NewsModelDto
import domain.services.Repository

class GetNewsByKeywordUseCase(private val repository: Repository) {
    fun execute(keywords: List<String>, sortByDate: Boolean): List<NewsModelDto> {
        val newsList = repository.getNews()
        val result = newsList.filter { it.keywords.containsAll(keywords) }
        return if (sortByDate) {
            result.sortedBy { it.date }
        } else {
            result
        }
    }
}