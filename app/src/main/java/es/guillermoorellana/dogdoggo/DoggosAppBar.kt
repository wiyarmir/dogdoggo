package es.guillermoorellana.dogdoggo

import androidx.compose.Composable
import androidx.compose.remember
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.layout.preferredHeight
import androidx.ui.layout.wrapContentHeight
import androidx.ui.material.MaterialTheme
import androidx.ui.text.annotatedString
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.*

@Composable
fun DoggosAppBar(
    collapsedFraction: Float,
    height: Dp
) {
    val titleCollapsedStartOffset =
        remember(collapsedFraction) { lerp(0.dp, 36.dp, collapsedFraction) }
    val titleCollapsedTopOffset =
        remember(collapsedFraction) { lerp(48.dp, 0.dp, collapsedFraction) }
    val titleMaxLines = remember(collapsedFraction) {
        if (collapsedFraction >= 0.85f) 1 else 3
    }

    val textStyle = MaterialTheme.typography.h6.copy(
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colors.onPrimary
    )

    val titleFontSize: TextUnit = lerp(
        textStyle.fontSize,
        MaterialTheme.typography.body1.fontSize,
        collapsedFraction
    )

    val text = annotatedString {
        append("Good boys")
    }

    Text(
        text = text,
        style = textStyle.copy(fontSize = titleFontSize),
        maxLines = titleMaxLines,
        modifier = Modifier.padding(
            start = 16.dp + titleCollapsedStartOffset,
            top = 16.dp + titleCollapsedTopOffset,
            bottom = 24.dp
        )
            .fillMaxWidth()
            .preferredHeight(height)
            .wrapContentHeight(Alignment.CenterVertically)
    )
}

@Preview
@Composable
fun preview() {
    DoggosAppBar(collapsedFraction = .0f, height = 160.dp)
}
