package domain.models

import com.fasterxml.jackson.annotation.JsonProperty

data class JsonModelDto(
    @JsonProperty("name")
    val name: String? = null,
    @JsonProperty("location")
    val location: String? = null,
    @JsonProperty("news")
    val newsModels: ArrayList<NewsModelDto> = arrayListOf()
)
