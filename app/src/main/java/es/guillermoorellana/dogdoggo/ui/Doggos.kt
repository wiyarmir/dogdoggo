package es.guillermoorellana.dogdoggo.ui

import androidx.compose.Composable
import androidx.ui.foundation.Text
import androidx.ui.livedata.observeAsState
import es.guillermoorellana.dogdoggo.BreedsAmbient
import es.guillermoorellana.dogdoggo.domain.BreedsViewState

@Composable
fun Doggos() {
    val breeds = BreedsAmbient.current.observeAsState(
        BreedsViewState()
    )
    breeds.value.breeds.forEach { breed ->
        Text(text = breed.text)
    }
}
