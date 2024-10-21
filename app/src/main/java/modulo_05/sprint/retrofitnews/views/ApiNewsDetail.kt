package modulo_05.sprint.retrofitnews.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.* // ktlint-disable no-wildcard-importsimport androidx.compose.runtime.Composable
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import modulo_05.sprint.retrofitnews.R
import modulo_05.sprint.retrofitnews.navigation.NavigationDestination



object DetailDestination : NavigationDestination {
    override val route = "detail"
    override val titleRes = R.string.detail_screen_title
   // const val articlemodel:ArticleModel
  //  val routeWithArgs = "${route}/{$articlemodel}"

}

@Composable
fun ApiNewsDetailScreen(
    article : ArticleModel,
    navigateBack: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    )
    {
//        AsyncImage(
//            model = article.urlToImage,
//            contentDescription = "My Image", // Provide a content description
//        )
        Text(
            text = article.title?:"",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = article.author?:"",
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = article.content?:"",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
//                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
//                //LocalContext.current.startActivity(intent)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        {
            Text(text = "Read more")
        }
    }
}

