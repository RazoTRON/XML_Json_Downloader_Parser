package app.dialogs

import domain.usecases.*

class NewsFakeViewModel(
    private val getAllNewsUseCase: GetAllNewsUseCase,
    private val getNewsByKeywordUseCase: GetNewsByKeywordUseCase
) {
    fun getAllNews() = getAllNewsUseCase.execute(sortByDate = true)
    fun getNewsByKeyword(keywords: List<String>) = getNewsByKeywordUseCase.execute(keywords, sortByDate = true)
}