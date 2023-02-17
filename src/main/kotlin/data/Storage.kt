package data

import domain.models.NewsModelDto

object Storage {
    var news: MutableList<NewsModelDto> = mutableListOf()
}