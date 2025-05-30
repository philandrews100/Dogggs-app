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
import kotlinx.coroutines.flow.map
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

    val allMainBreeds: StateFlow<List<String>> = state.map {
        it.breeds.map { breed -> breed.mainBreed }.distinct().sorted()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val selectedMainBreed = MutableStateFlow<String?>(null)

    val filteredBreeds: StateFlow<List<DogBreed>> = combine(
        state, searchQuery, selectedMainBreed
    ) { currentState, query, selectedMain ->
        currentState.breeds.filter { breed ->
            (selectedMain == null || breed.mainBreed.equals(selectedMain, ignoreCase = true)) &&
                    (query.isBlank() || breed.displayName.contains(query, ignoreCase = true))
        }
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
                            getBreedImageUseCase(breed.apiPath).url
                        } catch (e: Exception) {
                            null
                        }

                        _state.update { current ->
                            val updatedBreeds = current.breeds.map {
                                if (it.apiPath == breed.apiPath) it.copy(imageUrl = image)
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
