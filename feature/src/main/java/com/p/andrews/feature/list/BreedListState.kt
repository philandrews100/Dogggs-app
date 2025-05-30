package com.p.andrews.feature.list

import com.p.andrews.core.domain.model.DogBreed

data class BreedListState(
    val isLoading: Boolean = false,
    val breeds: List<DogBreed> = emptyList(),
    val error: String? = null
)