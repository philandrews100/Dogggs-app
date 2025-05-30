package com.p.andrews.feature.image

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.p.andrews.core.domain.model.DogImage
import com.p.andrews.core.domain.repository.DogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class BreedImagesUiState {
    data object Loading : BreedImagesUiState()
    data class Success(val images: List<DogImage>) : BreedImagesUiState()
    data class Error(val message: String) : BreedImagesUiState()
}

class BreedImagesViewModel(
    private val repository: DogRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<BreedImagesUiState>(BreedImagesUiState.Loading)
    val uiState: StateFlow<BreedImagesUiState> = _uiState

    fun loadImages(breed: String) {
        viewModelScope.launch {
            _uiState.value = BreedImagesUiState.Loading
            try {
                val result: List<DogImage> = repository.getImagesForBreed(breed)
                _uiState.value = BreedImagesUiState.Success(result)
            } catch (e: Exception) {
                _uiState.value = BreedImagesUiState.Error("Failed to load images")
            }
        }
    }
}