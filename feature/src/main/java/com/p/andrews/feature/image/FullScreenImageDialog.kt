package com.p.andrews.feature.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.p.andrews.style.AppTheme

@Composable
fun FullScreenImageDialog(
    imageUrl: String,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            tonalElevation = 4.dp,
            shadowElevation = 8.dp,
            color = AppTheme.colors.primary.cardBackground
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .background(Color.Transparent, RoundedCornerShape(12.dp))
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Dog Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .defaultMinSize(minWidth = 500.dp, minHeight = 500.dp) // Optional
                        .clip(RoundedCornerShape(12.dp))
                )
            }
        }
    }
}