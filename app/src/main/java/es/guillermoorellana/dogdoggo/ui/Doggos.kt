package es.guillermoorellana.dogdoggo.ui

import android.widget.Toast
import androidx.compose.Composable
import androidx.ui.core.ContextAmbient
import androidx.ui.foundation.Text
import androidx.ui.livedata.observeAsState
import androidx.ui.material.ListItem
import es.guillermoorellana.dogdoggo.BreedsStateAmbient
import es.guillermoorellana.dogdoggo.domain.BreedsViewState

@Composable
fun Doggos() {
    when (val viewState = BreedsStateAmbient.current.observeAsState(BreedsViewState.Loading).value) {
        BreedsViewState.Loading -> Text("Loading")
        BreedsViewState.Error -> Text("Error")
        is BreedsViewState.Loaded -> viewState.breeds.forEach { breed ->
            val context = ContextAmbient.current
            ListItem(text = breed.text, onClick = { Toast.makeText(context, breed.text, Toast.LENGTH_SHORT).show() })
        }
    } to "exhaustive"
}
