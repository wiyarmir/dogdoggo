package es.guillermoorellana.dogdoggo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Providers
import androidx.compose.ambientOf
import androidx.ui.core.setContent
import es.guillermoorellana.dogdoggo.domain.BreedListViewModel
import es.guillermoorellana.dogdoggo.ui.AppTheme
import es.guillermoorellana.dogdoggo.ui.BreedListScreen

class MainActivity : AppCompatActivity() {

    private val breedListViewModel: BreedListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Providers(
                    BreedListVMAmbient provides breedListViewModel
                ) {
                    BreedListScreen()
                }
            }
        }
    }
}

val BreedListVMAmbient = ambientOf<BreedListViewModel> { error("Uninitialised!") }
