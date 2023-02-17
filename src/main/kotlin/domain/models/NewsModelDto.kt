package domain.models

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Date

data class NewsModelDto(
    @JsonProperty("id")
    val id: Int? = null,
    @JsonProperty("title")
    val title: String? = null,
    @JsonProperty("description")
    val description: String? = null,
    @JsonProperty("date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss Z")
    val date: Date? = null,
    @JsonProperty("keywords")
    val keywords: ArrayList<String> = arrayListOf(),
    @JsonProperty("visible")
    val visible: Boolean? = null
)

fun List<NewsModelDto>.show() {
    this.forEach {
        println(
            """
                =================================
                Date: ${it.date}
                ID: ${it.id}
                Title: ${it.title}
                Keywords: ${it.keywords}
                
                Description: ${it.description}
            """.trimIndent()
        )
    }
}