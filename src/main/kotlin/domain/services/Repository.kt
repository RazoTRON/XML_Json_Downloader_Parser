package domain.services

import domain.models.NewsModelDto

interface Repository {
    fun saveNews(newsList: List<NewsModelDto>): Boolean
    fun getNews(): List<NewsModelDto>
    fun removeAllNews(): Boolean
}