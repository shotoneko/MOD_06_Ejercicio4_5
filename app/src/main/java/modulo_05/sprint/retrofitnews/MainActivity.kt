package modulo_05.sprint.retrofitnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import modulo_05.sprint.retrofitnews.data.News
import modulo_05.sprint.retrofitnews.navigation.NewsNavHost
import modulo_05.sprint.retrofitnews.ui.theme.RetrofitNewsTheme
import modulo_05.sprint.retrofitnews.views.ApiNewsScreen
import modulo_05.sprint.retrofitnews.views.HomeDestination

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetrofitNewsTheme {

               val navController = rememberNavController()
                NewsNavHost(navController = navController)
            }

        }
    }
}
