package es.guillermoorellana.dogdoggo

import androidx.compose.Composable
import androidx.compose.Providers
import androidx.compose.ambientOf
import androidx.ui.tooling.preview.Preview
import com.github.zsoltk.compose.router.BackStack
import com.github.zsoltk.compose.router.Router
import es.guillermoorellana.dogdoggo.data.DogBreed
import es.guillermoorellana.dogdoggo.ui.BreedDetailScreen
import es.guillermoorellana.dogdoggo.ui.BreedListScreen

@Preview
@Composable
fun App(defaultRouting: Routes = Routes.Breeds) {
    Router(defaultRouting) { backStack ->
        Providers(BackStackAmbient provides backStack) {
            when (val route = backStack.last()) {
                is Routes.Breeds -> BreedListScreen()
                is Routes.BreedDetail -> BreedDetailScreen(route.breed)
            } to "exhaustive"
        }
    }
}

val BackStackAmbient = ambientOf<BackStack<Routes>> { error("No backstack") }

sealed class Routes {
    object Breeds : Routes()
    data class BreedDetail(val breed: DogBreed) : Routes()
}
