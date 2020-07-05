/**
 * Copyright ??? unlicensed repo https://github.com/adrianblancode/Cheddar
 */

package co.adrianblan.ui

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.compose.*
import androidx.core.graphics.Insets
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat

val InsetsAmbient = ambientOf { Insets.NONE }

// Provides insets to children
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun InsetsWrapper(
    view: View,
    content: @Composable() () -> Unit
) {

    val insetState = state {
        view.rootWindowInsets?.systemWindowInsets?.let { Insets.toCompatInsets(it) }
            ?: Insets.NONE
    }

    onCommit {
        val listener = OnApplyWindowInsetsListener { _, windowInsets ->
            val inset = windowInsets.systemWindowInsets
            insetState.value = inset
            windowInsets
        }

        ViewCompat.setOnApplyWindowInsetsListener(view, listener)
        onDispose { ViewCompat.setOnApplyWindowInsetsListener(view, null) }
    }

    Providers(InsetsAmbient provides insetState.value) {
        content()
    }
}
