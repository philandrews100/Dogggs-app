package com.p.andrews.core.domain.repository

import com.p.andrews.core.data.remote.DogApiService
import com.p.andrews.core.domain.model.DogBreed
import com.p.andrews.core.domain.model.DogImage

class DogRepositoryImpl(
    private val api: DogApiService
) : DogRepository {

    override suspend fun getAllBreeds(): List<DogBreed> {
        return api.getAllBreeds()
            .message
            .keys
            .map { DogBreed(it) }
    }

    override suspend fun getImagesForBreed(breed: String): List<DogImage> {
        return api.getImagesForBreed(breed)
            .message
            .map { DogImage(it) }
    }

    override suspend fun getRandomImageForBreed(breed: String): DogImage {
        val dogImage = api.getSingleRandomImageForBreed(breed).message
        return DogImage(dogImage)

    }
}