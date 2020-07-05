package es.guillermoorellana.dogdoggo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Providers
import androidx.compose.ambientOf
import androidx.lifecycle.LiveData
import androidx.ui.core.setContent

class MainActivity : AppCompatActivity() {

    private val breedsViewModel: BreedsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Providers(BreedsAmbient provides breedsViewModel.breeds) {
                    App()
                }
            }
        }
    }
}

val BreedsAmbient = ambientOf<LiveData<BreedsViewState>> { error("Uninitialised!") }
