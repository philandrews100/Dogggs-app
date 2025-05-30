package com.p.andrews.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.p.andrews.style.R as drawables
import com.p.andrews.style.AppTheme

@Composable
fun NoResultsView(message: String = "No doggs found") {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter =  painterResource(drawables.drawable.sad),
                contentDescription = null,
                modifier = Modifier.padding(8.dp).fillMaxSize(0.2f)
            )
            Text(
                text = message,
                color = AppTheme.colors.textAndIcons.title
            )
        }
    }
}