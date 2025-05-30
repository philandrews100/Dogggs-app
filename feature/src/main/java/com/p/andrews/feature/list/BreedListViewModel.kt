package com.p.andrews.feature.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.p.andrews.core.domain.model.DogBreed
import com.p.andrews.core.domain.usecase.GetBreedImageUseCase
import com.p.andrews.core.domain.usecase.GetBreedsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BreedListViewModel(
    private val getBreedsUseCase: GetBreedsUseCase,
    private val getBreedImageUseCase: GetBreedImageUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(BreedListState())
    val state: StateFlow<BreedListState> = _state

    var searchQuery = MutableStateFlow("")

    val filteredBreeds: StateFlow<List<DogBreed>> = combine(
        state,
        searchQuery
    ) { currentState, query ->
        if (query.isBlank()) currentState.breeds
        else currentState.breeds.filter { it.name.contains(query, ignoreCase = true) }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        loadBreeds()
    }

    fun retry() {
        _state.update { it.copy(error = null, isLoading = true) }
        loadBreeds()
    }

    private fun loadBreeds() {
        viewModelScope.launch {
            try {
                val breeds = getBreedsUseCase()

                // Show list without images first
                _state.update { it.copy(breeds = breeds, isLoading = false) }

                // Load images per breed in parallel
                breeds.forEach { breed ->
                    viewModelScope.launch {
                        val image = try {
                            getBreedImageUseCase(breed.name).url
                        } catch (e: Exception) {
                            null
                        }

                        _state.update { current ->
                            val updatedBreeds = current.breeds.map {
                                if (it.name == breed.name) it.copy(imageUrl = image)
                                else it
                            }
                            current.copy(breeds = updatedBreeds)
                        }
                    }
                }

            } catch (e: Exception) {
                _state.update { it.copy(error = e.message, isLoading = false) }
            }
        }
    }
}