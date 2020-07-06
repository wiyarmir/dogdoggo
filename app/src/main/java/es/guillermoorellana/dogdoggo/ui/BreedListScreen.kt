package es.guillermoorellana.dogdoggo.ui

import androidx.compose.Composable
import androidx.ui.core.ContextAmbient
import androidx.ui.core.DensityAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.layout.Spacer
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.padding
import androidx.ui.layout.preferredHeight
import androidx.ui.livedata.observeAsState
import androidx.ui.material.Button
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.ListItem
import androidx.ui.material.Scaffold
import androidx.ui.unit.Dp
import androidx.ui.unit.dp
import androidx.ui.unit.px
import co.adrianblan.ui.InsetsAmbient
import es.guillermoorellana.dogdoggo.BreedListVMAmbient
import es.guillermoorellana.dogdoggo.DetailActivity
import es.guillermoorellana.dogdoggo.domain.BreedsViewState

@Composable
fun BreedListScreen() {
    Scaffold {
        val scroller = ScrollerPosition()
        val toolbarMinHeight = 56.dp
        val toolbarMaxHeight = 160.dp
        VerticalScroller(scroller) {
            val topInsets = with(DensityAmbient.current) {
                InsetsAmbient.current.top.px.toDp()
            }
            Spacer(
                modifier = Modifier.preferredHeight(toolbarMaxHeight + topInsets)
            )
            BreedList()
            Spacer(
                modifier = Modifier.preferredHeight(toolbarMinHeight)
            )
        }
        CollapsingToolbar(
            scroller = scroller,
            minHeight = toolbarMinHeight,
            maxHeight = toolbarMaxHeight,
            toolbarContent = { collapsedFraction: Float, height: Dp ->
                DoggosAppBar(collapsedFraction, height)
            }
        )
    }
}

@Composable
fun BreedList() {
    val breedsViewModel = BreedListVMAmbient.current
    when (val viewState = breedsViewModel.state.observeAsState().value!!) {
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
        is BreedsViewState.Loaded -> {
            val context = ContextAmbient.current
            viewState.breeds.forEach { displayBreed ->
                ListItem(
                    text = displayBreed.text,
                    onClick = {
                        context.startActivity(
                            DetailActivity.newIntent(
                                context,
                                displayBreed.breed,
                                displayBreed.subBreed
                            )
                        )
                    }
                )
            }
        }
    } to "exhaustive"
}
