package com.p.andrews.core.domain.repository

import com.p.andrews.core.domain.model.DogBreed
import com.p.andrews.core.domain.model.DogImage

interface DogRepository {
    suspend fun getAllBreeds(): List<DogBreed>
    suspend fun getImagesForBreed(breed: String): List<DogImage>
    suspend fun getRandomImageForBreed(breed: String): DogImage
}