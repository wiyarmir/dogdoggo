package es.guillermoorellana.dogdoggo

import androidx.compose.Composable
import androidx.ui.tooling.preview.Preview
import com.github.zsoltk.compose.router.Router
import es.guillermoorellana.dogdoggo.data.DogBreed
import es.guillermoorellana.dogdoggo.ui.BreedsScreen

@Preview
@Composable
fun App(defaultRouting: Routes = Routes.Breeds) {
    Router(defaultRouting) { backStack ->
        when (val route = backStack.last()) {
            is Routes.Breeds -> BreedsScreen()
            is Routes.BreedDetail -> TODO()
        } to "exhaustive"
    }
}

sealed class Routes {
    object Breeds : Routes()
    data class BreedDetail(val breed: DogBreed) : Routes()
}
