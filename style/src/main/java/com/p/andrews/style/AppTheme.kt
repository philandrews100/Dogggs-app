package com.p.andrews.style

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

object AppTheme {
    val colors: AppColors
    @Composable
    @ReadOnlyComposable
    get() = LocalSLSColors.current

    val shapes: AppShapes
    @Composable
    @ReadOnlyComposable
    get() = LocalSLSShapes.current
}

@Composable
fun AppTheme(
    isDarkMode: Boolean = isSystemInDarkTheme(),
    composeContent: @Composable () -> Unit
) {
    val colors = if(isDarkMode) DarkColors else LightColors
    val shapes = MainShapes

    CompositionLocalProvider(
        LocalSLSColors provides colors,
        LocalSLSShapes provides shapes,
        content = composeContent
    )
}