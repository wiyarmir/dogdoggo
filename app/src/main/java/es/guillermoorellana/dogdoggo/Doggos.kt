package es.guillermoorellana.dogdoggo

import androidx.compose.Composable
import androidx.ui.foundation.Text
import androidx.ui.livedata.observeAsState

@Composable
fun Doggos() {
    val breeds = BreedsAmbient.current.observeAsState(BreedsViewState())
    breeds.value.breeds.forEach { breed ->
        Text(text = breed.text)
    }
}
