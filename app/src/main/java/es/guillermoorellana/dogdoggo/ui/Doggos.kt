package es.guillermoorellana.dogdoggo.ui

import android.widget.Toast
import androidx.compose.Composable
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Text
import androidx.ui.layout.Spacer
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.padding
import androidx.ui.livedata.observeAsState
import androidx.ui.material.Button
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.ListItem
import androidx.ui.unit.dp
import es.guillermoorellana.dogdoggo.BreedsVMAmbient
import es.guillermoorellana.dogdoggo.domain.BreedsViewState

@Composable
fun Doggos() {
    val breedsViewModel = BreedsVMAmbient.current
    when (val viewState = breedsViewModel.state.observeAsState(BreedsViewState.Loading).value) {
        is BreedsViewState.Loading ->
            Box(
                modifier = Modifier.fillMaxSize().padding(32.dp),
                gravity = ContentGravity.Center
            ) {
                CircularProgressIndicator()
            }
        is BreedsViewState.Error ->
            Box(
                modifier = Modifier.fillMaxSize().padding(32.dp),
                gravity = ContentGravity.Center
            ) {
                Text("Error")
                Spacer(modifier = Modifier.padding(8.dp))
                Button(onClick = { breedsViewModel.fetchBreeds() }) {
                    Text("Try again")
                }
            }
        is BreedsViewState.Loaded ->
            viewState.breeds.forEach { breed ->
                val context = ContextAmbient.current
                ListItem(
                    text = breed.text,
                    onClick = { Toast.makeText(context, breed.text, Toast.LENGTH_SHORT).show() })
            }
    } to "exhaustive"
}
