package es.guillermoorellana.dogdoggo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Providers
import androidx.compose.ambientOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.ui.core.setContent
import es.guillermoorellana.dogdoggo.domain.BreedDetailViewModel
import es.guillermoorellana.dogdoggo.ui.AppTheme
import es.guillermoorellana.dogdoggo.ui.BreedDetailScreen

class DetailActivity : AppCompatActivity() {

    private val breedDetailViewModelFactory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            modelClass.getConstructor(String::class.java, String::class.java)
                .newInstance(intent.getStringExtra(EXTRA_BREED), intent.getStringExtra(EXTRA_SUBBREED))
    }

    private val breedDetailViewModel: BreedDetailViewModel by viewModels { breedDetailViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Providers(
                    BreedDetailVMAmbient provides breedDetailViewModel
                ) {
                    BreedDetailScreen()
                }
            }
        }
    }

    companion object {
        private const val EXTRA_BREED = "extra:breed"
        private const val EXTRA_SUBBREED = "extra:subBreed"
        fun newIntent(context: Context, breed: String, subBreed: String?) = Intent(context, DetailActivity::class.java)
            .putExtra(EXTRA_BREED, breed)
            .putExtra(EXTRA_SUBBREED, subBreed)
    }
}

val BreedDetailVMAmbient = ambientOf<BreedDetailViewModel> { error("Uninitialised!") }
