package modulo_05.sprint.retrofitnews.data

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)