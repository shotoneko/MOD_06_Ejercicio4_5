package modulo_05.sprint.retrofitnews.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import modulo_05.sprint.retrofitnews.data.NewsApiService
import modulo_05.sprint.retrofitnews.data.NewsPagingSource
import modulo_05.sprint.retrofitnews.views.ArticleModel
import javax.inject.Inject

class NewsRepository @Inject constructor(private val api: NewsApiService) {
    companion object{
        const val PAGE_SIZE = 10
        const val PREFETCH_ITEMS = 5
    }

    fun getAllNews(): Flow<PagingData<ArticleModel>> {
       return Pager(
           config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                prefetchDistance = PREFETCH_ITEMS
              ),

           pagingSourceFactory = { NewsPagingSource(api) }

       ).flow
    }
}
