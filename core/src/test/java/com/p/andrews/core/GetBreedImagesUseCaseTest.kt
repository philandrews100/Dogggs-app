package com.p.andrews.core

import com.p.andrews.core.domain.model.DogImage
import com.p.andrews.core.domain.repository.DogRepository
import com.p.andrews.core.domain.usecase.GetBreedImagesUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*
import kotlin.test.assertEquals

class GetBreedImagesUseCaseTest {

    private lateinit var repository: DogRepository
    private lateinit var useCase: GetBreedImagesUseCase

    @Before
    fun setup() {
        repository = mock()
        useCase = GetBreedImagesUseCase(repository)
    }

    @Test
    fun `invoke returns images from repository`() = runTest {
        // Given
        val breed = "bulldog"
        val expectedImages = listOf(
            DogImage("https://example.com/image1.jpg"),
            DogImage("https://example.com/image2.jpg")
        )
        whenever(repository.getImagesForBreed(breed)).thenReturn(expectedImages)

        // When
        val result = useCase(breed)

        // Then
        assertEquals(expectedImages, result)
    }
}