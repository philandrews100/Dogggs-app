package com.p.andrews.feature.image

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.p.andrews.core.ui.DogImageCard
import com.p.andrews.style.AppTheme
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedImagesScreen(
    breed: String,
    viewModel: BreedImagesViewModel = koinViewModel(),
    onBack: () -> Unit = {}
) {
    LaunchedEffect(breed) {
        viewModel.loadImages(breed)
    }

    val state by viewModel.uiState.collectAsState()
    var selectedImage by remember { mutableStateOf<String?>(null) }

    if (selectedImage != null) {
        FullScreenImageDialog(
            imageUrl = selectedImage!!,
            onDismiss = { selectedImage = null }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = breed.replaceFirstChar { it.uppercaseChar() },
                        color = AppTheme.colors.textAndIcons.title
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = AppTheme.colors.textAndIcons.icon
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppTheme.colors.primary.cardBackground,
                    navigationIconContentColor = AppTheme.colors.textAndIcons.icon,
                    titleContentColor = AppTheme.colors.textAndIcons.title
                )
            )
        },
        contentColor = AppTheme.colors.primary.background,
        containerColor = AppTheme.colors.primary.background
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (state) {
                is BreedImagesUiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is BreedImagesUiState.Success -> {
                    val images = (state as BreedImagesUiState.Success).images
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(images) { dog ->
                            DogImageCard(
                                imageUrl = dog.url,
                                onClick = { selectedImage = dog.url }
                            )
                        }
                    }
                }

                is BreedImagesUiState.Error -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = (state as BreedImagesUiState.Error).message)
                    }
                }
            }

        }
    }
}