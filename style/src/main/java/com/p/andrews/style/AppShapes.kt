package com.p.andrews.style

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

internal val MainShapes
    get() = AppShapes(
        bottomContainer = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomEnd = 0.dp,
            bottomStart = 0.dp
        ),
        cardContainer = RoundedCornerShape(20.dp)
    )

data class AppShapes(
    val bottomContainer: Shape,
    val cardContainer: Shape
)

internal val LocalSLSShapes = staticCompositionLocalOf {
    AppShapes(
        bottomContainer = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomEnd = 0.dp,
            bottomStart = 0.dp
        ),
        cardContainer = RoundedCornerShape(8.dp)
    )
}