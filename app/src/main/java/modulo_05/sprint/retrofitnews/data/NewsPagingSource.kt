package modulo_05.sprint.retrofitnews.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import modulo_05.sprint.retrofitnews.views.ArticleModel
import javax.inject.Inject

class NewsPagingSource @Inject constructor(private val api:NewsApiService) : PagingSource<Int, ArticleModel>() {

    override fun getRefreshKey(state: PagingState<Int, ArticleModel>): Int? {
        return state.anchorPosition

    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleModel> {
        try {
            val nextPageNumber = params.key ?: 1
            val response = api.getNewsPaging(page = nextPageNumber, pageSize = params.loadSize)
            val articles: List<Article> = response.articles
            val prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null
            val nextKey = if (response.totalResults > nextPageNumber * params.loadSize) nextPageNumber + 1 else null
            return LoadResult.Page(
                data = articles.map { it.toArticleModel() },
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            Log.d("NewsPagingSource", "Error loading news: ${e.message}")
            return LoadResult.Error(e)
        }
    }
}

fun Article.toArticleModel() = ArticleModel(
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    source = source.name,
    title = title,
    //url = url,
  //  urlToImage = urlToImage
)