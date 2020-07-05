package es.guillermoorellana.dogdoggo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Providers
import androidx.compose.ambientOf
import androidx.ui.core.setContent
import com.github.zsoltk.compose.backpress.AmbientBackPressHandler
import com.github.zsoltk.compose.backpress.BackPressHandler
import es.guillermoorellana.dogdoggo.domain.BreedDetailViewModel
import es.guillermoorellana.dogdoggo.domain.BreedListViewModel
import es.guillermoorellana.dogdoggo.ui.AppTheme

class MainActivity : AppCompatActivity() {

    private val backPressHandler = BackPressHandler()

    private val breedListViewModel: BreedListViewModel by viewModels()
    private val breedDetailViewModel: BreedDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Providers(
                    BreedListVMAmbient provides breedListViewModel,
                    BreedDetailVMAmbient provides breedDetailViewModel,
                    AmbientBackPressHandler provides backPressHandler
                ) {
                    App()
                }
            }
        }
    }

    override fun onBackPressed() {
        if (!backPressHandler.handle()) {
            super.onBackPressed()
        }
    }
}

val BreedListVMAmbient = ambientOf<BreedListViewModel> { error("Uninitialised!") }
val BreedDetailVMAmbient = ambientOf<BreedDetailViewModel> { error("Uninitialised!") }
