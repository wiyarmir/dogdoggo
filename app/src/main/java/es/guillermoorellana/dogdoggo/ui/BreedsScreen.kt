package es.guillermoorellana.dogdoggo.ui

import android.widget.Toast
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
import es.guillermoorellana.dogdoggo.BreedsVMAmbient
import es.guillermoorellana.dogdoggo.domain.BreedsViewState

@Composable
fun BreedsScreen() {
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
            Doggos()
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
