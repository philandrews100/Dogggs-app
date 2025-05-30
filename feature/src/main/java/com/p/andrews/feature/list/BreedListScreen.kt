package com.p.andrews.feature.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.p.andrews.core.ui.BreedListItem
import com.p.andrews.core.ui.ErrorMessage
import com.p.andrews.core.ui.LoadingState
import com.p.andrews.style.AppTheme
import com.p.andrews.style.MultiThemePreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun BreedListScreen(
    viewModel: BreedListViewModel = koinViewModel(),
    onBreedSelected: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()
    val filteredBreeds by viewModel.filteredBreeds.collectAsState()
    var searchInput by remember { mutableStateOf("") }
    val debounceScope = rememberCoroutineScope()

    // Debounced search
    LaunchedEffect(searchInput) {
        debounceScope.launch {
            delay(300)
            viewModel.searchQuery.value = searchInput
        }
    }

    when {
        state.isLoading -> LoadingState()
        state.error != null -> ErrorMessage(
            state.error ?: "Unknown error",
            onRetry = { viewModel.retry() }
        )

        else -> Scaffold(
            topBar = {
                BreedSearchBar(query = searchInput, onQueryChanged = { searchInput = it })
            },
            contentColor = AppTheme.colors.primary.background,
            containerColor = AppTheme.colors.primary.background
        ) { innerPadding ->
            if (filteredBreeds.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No breeds found.", color = AppTheme.colors.textAndIcons.desc)
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(filteredBreeds) { breed ->
                        BreedListItem(
                            name = breed.name,
                            imageUrl = breed.imageUrl
                        ) {
                            onBreedSelected(breed.name)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BreedSearchBar(
    query: String,
    onQueryChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChanged,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        placeholder = {
            Text(
                text = "Search breeds...",
                color = AppTheme.colors.textAndIcons.desc
            )
        },
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = AppTheme.colors.textAndIcons.title,
            unfocusedTextColor = AppTheme.colors.textAndIcons.title,
            cursorColor = AppTheme.colors.textAndIcons.title,
            focusedBorderColor = AppTheme.colors.textAndIcons.title,
            unfocusedBorderColor = AppTheme.colors.textAndIcons.desc
        )
    )
}

@MultiThemePreview
@Composable
fun BreedListScreenPreview() {
    AppTheme {
        BreedListScreen(onBreedSelected = {})
    }
}