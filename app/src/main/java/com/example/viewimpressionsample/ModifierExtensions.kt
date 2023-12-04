package com.example.viewimpressionsample

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.InspectorValueInfo
import androidx.compose.ui.platform.debugInspectorInfo
import com.example.viewimpressionsample.ui.theme.VImpComplete
import com.example.viewimpressionsample.ui.theme.VImpInProgress
import kotlinx.coroutines.delay

@Composable
fun Modifier.viewImpression(id: Int): Modifier {
    var state by remember {
        mutableStateOf(ImpressionState.NOT_IMPRESSION)
    }

    LaunchedEffect(key1 = state) {
        if (state == ImpressionState.IN_PROGRESS) {
            delay(100L)
            state = ImpressionState.COMPLETE
        }
    }

    return this then
            onGloballyPositioned { lc ->
                val visible = lc.boundsInWindow()
                val fullView = lc.boundsInParent()
                val heightPercent = visible.height / fullView.height

                if (visible.height / fullView.height >= 0.5) {
                    if (state == ImpressionState.NOT_IMPRESSION) {
                        state = ImpressionState.IN_PROGRESS
                    }
                } else {
                    if (state != ImpressionState.NOT_IMPRESSION) {
                        state = ImpressionState.NOT_IMPRESSION
                    }
                }

            }
                .foreground(state.color)
}

enum class ImpressionState(val color: Color) {
    NOT_IMPRESSION(Color.Transparent),
    IN_PROGRESS(VImpInProgress),
    COMPLETE(VImpComplete),
}


fun Modifier.foreground(color: Color): Modifier =
    this then (Foreground(color, debugInspectorInfo {
        name = "foreground"
        value = color
        properties["color"] = color
    }))


private class Foreground(
    private val color: Color,
    inspectorInfo: InspectorInfo.() -> Unit
) : DrawModifier, InspectorValueInfo(inspectorInfo) {
    override fun ContentDrawScope.draw() {
        drawContent()
        drawRect(color)
    }
}
