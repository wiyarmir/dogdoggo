package es.guillermoorellana.dogdoggo

import android.app.Activity
import androidx.compose.Composable
import androidx.compose.onCommit
import androidx.ui.foundation.isSystemInDarkTheme
import androidx.ui.graphics.toArgb
import androidx.ui.material.ColorPalette
import androidx.ui.material.MaterialTheme

@Composable
fun Activity.AppTheme(content: @Composable() () -> Unit) {
    MaterialTheme(colors = colors()) {
        val statusBarColor = with(MaterialTheme.colors) {
            if (isLight) darkenedPrimary else surface.toArgb()
        }
        onCommit(statusBarColor) {
            window.statusBarColor = statusBarColor
        }
        content()
    }
}

private val ColorPalette.darkenedPrimary: Int
    get() = with(primary) {
        copy(
            red = red * 0.75f,
            green = green * 0.75f,
            blue = blue * 0.75f
        )
    }.toArgb()

@Composable
fun colors() = if (isSystemInDarkTheme()) darkColorPalette else lightColorPalette
