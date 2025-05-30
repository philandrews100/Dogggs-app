package com.p.andrews.core.domain.usecase

import com.p.andrews.core.domain.model.DogImage
import com.p.andrews.core.domain.repository.DogRepository

class GetBreedImagesUseCase(
    private val repository: DogRepository
) {
    suspend operator fun invoke(breed: String): List<DogImage> {
        return repository.getImagesForBreed(breed)
    }
}