package es.guillermoorellana.dogdoggo.ui

import androidx.compose.Composable
import androidx.ui.core.DensityAmbient
import androidx.ui.core.Modifier
import androidx.ui.core.WithConstraints
import androidx.ui.foundation.AdapterList
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Text
import androidx.ui.layout.Spacer
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.layout.preferredHeight
import androidx.ui.livedata.observeAsState
import androidx.ui.material.Button
import androidx.ui.material.Card
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.unit.dp
import coil.size.Scale
import com.luca992.compose.image.CoilImage
import es.guillermoorellana.dogdoggo.BreedDetailVMAmbient
import es.guillermoorellana.dogdoggo.R
import es.guillermoorellana.dogdoggo.domain.BreedDetailViewState
import es.guillermoorellana.dogdoggo.domain.asBreedDisplayText
import es.guillermoorellana.dogdoggo.domain.dogBreed

@Composable
fun BreedDetailScreen() {
    val viewModel = BreedDetailVMAmbient.current
    Scaffold(
        topAppBar = { TopAppBar(title = { Text(viewModel.dogBreed.asBreedDisplayText()) }) }
    ) {
        when (val viewState = viewModel.state.observeAsState().value!!) {
            is BreedDetailViewState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(32.dp),
                    gravity = ContentGravity.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is BreedDetailViewState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(32.dp),
                    gravity = ContentGravity.Center
                ) {
                    Text("Error")
                    Spacer(modifier = Modifier.padding(8.dp))
                    Button(onClick = { viewModel.fetchPhotos() }) {
                        Text("Try again")
                    }
                }
            }
            is BreedDetailViewState.Loaded -> {
                AdapterList(data = viewState.photos) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        WithConstraints {
                            val boxWidth = with(DensityAmbient.current) { constraints.maxWidth.toDp() }
                            val boxHeight = boxWidth * 3f / 4f
                            CoilImage(
                                model = it,
                                modifier = Modifier
                                    .preferredHeight(boxHeight)
                            ) {
                                crossfade(true)
                                placeholder(R.drawable.placeholder)
                                scale(Scale.FILL)
                            }
                        }
                    }
                }
            }
        } to "exhaustive"
    }
}
