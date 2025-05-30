package com.p.andrews.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.p.andrews.style.AppTheme
import com.p.andrews.style.MultiThemePreview

@Composable
fun BreedListItem(
    name: String,
    imageUrl: String?,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.primary.cardBackground,
            contentColor = AppTheme.colors.textAndIcons.desc,
            disabledContainerColor = AppTheme.colors.primary.overlayColor,
            disabledContentColor = AppTheme.colors.textAndIcons.desc
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(10.dp))
            ) {
                if (imageUrl != null) {
                    DogImage(
                        imageUrl = imageUrl,
                        modifier = Modifier.matchParentSize()
                    )
                } else {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.Center)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = name.replaceFirstChar { it.uppercaseChar() },
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}


@MultiThemePreview
@Composable
fun BreedListItemPreview() {
    AppTheme {
        BreedListItem(
            name = "bulldog",
            imageUrl = "https://images.dog.ceo/breeds/lhasa/n02098413_7910.jpg",
            onClick = {}
        )
    }
}