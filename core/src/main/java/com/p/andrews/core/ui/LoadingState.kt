package com.p.andrews.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.p.andrews.style.AppTheme
import com.p.andrews.style.MultiThemePreview

@Composable
fun LoadingState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colors.primary.cardBackground, AppTheme.shapes.cardContainer),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@MultiThemePreview
@Composable
fun LoadingStatePreview() {
    AppTheme {
        LoadingState()
    }
}
