package es.guillermoorellana.dogdoggo.ui

import androidx.compose.Composable
import androidx.ui.core.DensityAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.ScrollerPosition
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Spacer
import androidx.ui.layout.preferredHeight
import androidx.ui.material.Scaffold
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.Dp
import androidx.ui.unit.dp
import androidx.ui.unit.px
import co.adrianblan.ui.InsetsAmbient

@Preview
@Composable
fun App() {
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

