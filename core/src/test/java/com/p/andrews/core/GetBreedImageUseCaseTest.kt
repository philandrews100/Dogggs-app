package com.p.andrews.core

import com.p.andrews.core.domain.model.DogImage
import com.p.andrews.core.domain.repository.DogRepository
import com.p.andrews.core.domain.usecase.GetBreedImageUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*
import kotlin.test.assertEquals

class GetBreedImageUseCaseTest {

    private lateinit var repository: DogRepository
    private lateinit var useCase: GetBreedImageUseCase

    @Before
    fun setup() {
        repository = mock()
        useCase = GetBreedImageUseCase(repository)
    }

    @Test
    fun `invoke returns a random image for breed`() = runTest {
        // Given
        val breed = "beagle"
        val expectedImage = DogImage("https://example.com/beagle.jpg")
        whenever(repository.getRandomImageForBreed(breed)).thenReturn(expectedImage)

        // When
        val result = useCase(breed)

        // Then
        assertEquals(expectedImage, result)
    }
}