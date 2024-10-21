package modulo_05.sprint.retrofitnews.data

import modulo_05.sprint.retrofitnews.data.Constants.Companion.API_KEY
import modulo_05.sprint.retrofitnews.data.Constants.Companion.ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET(ENDPOINT + API_KEY)
    suspend fun getNews(): Response<News>

    @GET(ENDPOINT + API_KEY)
    suspend fun getNewsPaging(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int) :News

}
