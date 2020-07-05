package es.guillermoorellana.dogdoggo.ui

import androidx.compose.Composable
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import es.guillermoorellana.dogdoggo.data.DogBreed
import es.guillermoorellana.dogdoggo.domain.displayText

@Composable
fun BreedDetailScreen(breed: DogBreed) {
    Scaffold(
        topAppBar = { TopAppBar(title = { Text(breed.displayText()) }) }
    ) {
        VerticalScroller {

        }
    }
}
