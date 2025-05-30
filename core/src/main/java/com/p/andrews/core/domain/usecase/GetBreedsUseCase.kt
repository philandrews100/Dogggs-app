package com.p.andrews.core.domain.usecase

import com.p.andrews.core.domain.model.DogBreed
import com.p.andrews.core.domain.repository.DogRepository

class GetBreedsUseCase(
    private val repository: DogRepository
) {
    suspend operator fun invoke(): List<DogBreed> {
        return repository.getAllBreeds()
    }
}