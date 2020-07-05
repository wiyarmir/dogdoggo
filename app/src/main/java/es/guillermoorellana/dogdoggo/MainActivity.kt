package es.guillermoorellana.dogdoggo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Providers
import androidx.compose.ambientOf
import androidx.ui.core.setContent
import com.github.zsoltk.compose.backpress.AmbientBackPressHandler
import com.github.zsoltk.compose.backpress.BackPressHandler
import es.guillermoorellana.dogdoggo.domain.BreedsViewModel
import es.guillermoorellana.dogdoggo.ui.AppTheme

class MainActivity : AppCompatActivity() {

    private val backPressHandler = BackPressHandler()

    private val breedsViewModel: BreedsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Providers(
                    BreedsVMAmbient provides breedsViewModel,
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

val BreedsVMAmbient = ambientOf<BreedsViewModel> { error("Uninitialised!") }
