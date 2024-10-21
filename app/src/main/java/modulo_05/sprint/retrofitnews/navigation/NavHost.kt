package modulo_05.sprint.retrofitnews.navigation

import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import modulo_05.sprint.retrofitnews.data.Article
import modulo_05.sprint.retrofitnews.views.ApiNewsDetailScreen
import modulo_05.sprint.retrofitnews.views.ApiNewsScreen
import modulo_05.sprint.retrofitnews.views.ArticleModel
import modulo_05.sprint.retrofitnews.views.HomeDestination
import kotlin.reflect.typeOf


@Composable
fun NewsNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    )
    {
        composable<NavRoute.MainScreen>{
            ApiNewsScreen(
                navigateToDetailScreen = { article ->
                    navController.navigate(NavRoute.DetailScreen(article))
                }
            )
        }

        composable<NavRoute.DetailScreen>(
            typeMap = mapOf(typeOf<ArticleModel>() to NavType.mapper<ArticleModel>()),
        ) {
            val article = it.toRoute<NavRoute.DetailScreen>().article
            ApiNewsDetailScreen(
                article = article,
                navigateBack = { navController.popBackStack()},

            )
        }



    }
}
sealed interface NavRoute{

    @Serializable
    @SerialName("MainScreen")
    object MainScreen : NavRoute

    @Serializable
    data class DetailScreen(val article: ArticleModel) : NavRoute

}