package data

import domain.models.NewsModelDto
import domain.services.Repository

class RepositoryImpl(private val storage: Storage) : Repository {
    override fun saveNews(newsList: List<NewsModelDto>): Boolean {
        removeAllNews()
        return storage.news.addAll(newsList)
    }

    override fun getNews(): List<NewsModelDto> {
        return storage.news
    }

    override fun removeAllNews(): Boolean {
        storage.news.clear()
        return storage.news.isEmpty()
    }
}