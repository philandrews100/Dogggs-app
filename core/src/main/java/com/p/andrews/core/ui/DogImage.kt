package com.p.andrews.core.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.p.andrews.style.R as drawables
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size

@Composable
fun DogImage(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    val context = LocalContext.current

    val request = ImageRequest.Builder(context)
        .data(imageUrl)
        .crossfade(true)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .diskCachePolicy(CachePolicy.ENABLED)
        .placeholder(drawables.drawable.loading)
        .error(drawables.drawable.sad)
        .build()

    SubcomposeAsyncImage(
        model = request,
        contentDescription = null,
        contentScale = contentScale,
        modifier = modifier
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Loading -> {
                Image(
                    painter = painter,
                    contentDescription = null,
                    alpha = 0.4f,
                    modifier = Modifier.size(10.dp) // or any small size
                )
            }

            is AsyncImagePainter.State.Error -> {
                // Error icon - slightly bigger
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }

            is AsyncImagePainter.State.Success -> {
                // Full image - use provided modifier
                SubcomposeAsyncImageContent()
            }

            else -> {
                // Default fallback
                SubcomposeAsyncImageContent()
            }
        }
    }
}