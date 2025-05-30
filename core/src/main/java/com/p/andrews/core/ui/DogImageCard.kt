package com.p.andrews.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.p.andrews.style.AppTheme
import com.p.andrews.style.MultiThemePreview

@Composable
fun DogImageCard(
    imageUrl: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        shape = AppTheme.shapes.cardContainer,
        modifier = modifier
            .padding(8.dp)
            .aspectRatio(1f)
            .clickable(onClick = onClick),
        colors = CardColors(
            containerColor = AppTheme.colors.primary.cardBackground,
            contentColor = AppTheme.colors.textAndIcons.desc,
            disabledContainerColor = AppTheme.colors.primary.overlayColor,
            disabledContentColor = AppTheme.colors.textAndIcons.desc
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@MultiThemePreview
@Composable
fun DogImageCardPreview() {
    AppTheme {
        DogImageCard(
            imageUrl = "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg"
        ) {

        }
    }
}
