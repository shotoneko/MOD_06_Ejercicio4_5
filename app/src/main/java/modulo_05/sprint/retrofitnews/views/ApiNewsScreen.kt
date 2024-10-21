package modulo_05.sprint.retrofitnews.views
import android.os.Parcelable
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import modulo_05.sprint.retrofitnews.R
import modulo_05.sprint.retrofitnews.navigation.NavigationDestination
import modulo_05.sprint.retrofitnews.util.StringSanitizer


object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.home_screen_title
}
@Composable
fun ApiNewsScreen(
    viewModel: ApiNewsScreenViewModel = hiltViewModel(),
    //navController: NavController
    navigateToDetailScreen: (ArticleModel) -> Unit

) {
    val articles = viewModel.articles.collectAsLazyPagingItems()
    NewsList(articles, navigateToDetailScreen)

}

@Composable
fun NewsList(articles: LazyPagingItems<ArticleModel>, navigateToDetailScreen: (ArticleModel) -> Unit) {
    LazyColumn {
        items(articles.itemCount) { index ->
            articles[index]?.let { article ->
                Log.d("NewsList", "Article: $article")
                NewsCard(
                    article,
                    navigateToDetailScreen = navigateToDetailScreen
                )
            }
        }
    }
}

@Composable
fun NewsCard(article: ArticleModel, navigateToDetailScreen: (ArticleModel) -> Unit) {
    Box(modifier = Modifier
        .clickable { navigateToDetailScreen(article) }
        .padding(16.dp)
        .fillMaxWidth()
        .background(Color.Black)) {
        Column {
            Text(
                text = article.title ?: "",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = article.description ?: "",
                color = Color.White,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = article.publishedAt ?: "",
                color = Color.White,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }

}
@Parcelize
@Serializable
data class ArticleModel(
    val author: String? = "",
    val content: String? = "",
    val description: String? = "",
    val publishedAt: String? = "",
    val source: String? = "",
    val title: String? = "",
    @Serializable(with = StringSanitizer::class)
    val url: String? = "",
    @Serializable(with = StringSanitizer::class)
    val urlToImage: String? = ""
): Parcelable