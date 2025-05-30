package com.p.andrews.core

import com.p.andrews.core.data.model.BreedResponse
import com.p.andrews.core.data.model.ImageResponse
import com.p.andrews.core.data.model.SingleImageResponse
import com.p.andrews.core.data.remote.DogApiService
import com.p.andrews.core.domain.model.DogBreed
import com.p.andrews.core.domain.model.DogImage
import com.p.andrews.core.domain.repository.DogRepository
import com.p.andrews.core.domain.repository.DogRepositoryImpl
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class DogRepositoryImplTest {

    private lateinit var api: DogApiService
    private lateinit var repository: DogRepository

    @Before
    fun setup() {
        api = mock()
        repository = DogRepositoryImpl(api)
    }

    @Test
    fun `getAllBreeds returns list of DogBreed`() = runTest {
        // Given
        whenever(api.getAllBreeds()).thenReturn(
            BreedResponse(
                message = mapOf("bulldog" to listOf("english", "french")),
                status = "success"
            )
        )

        // When
        val result = repository.getAllBreeds()

        // Then
        assertEquals(listOf(DogBreed("bulldog", "english"), DogBreed("bulldog", "french")), result)
    }

    @Test
    fun `getImagesForBreed returns list of DogImage`() = runTest {
        // Given
        whenever(api.getImagesForBreed("beagle")).thenReturn(
            ImageResponse(
                message = listOf("url1", "url2"),
                status = "success"
            )
        )

        // When
        val result = repository.getImagesForBreed("beagle")

        // Then
        assertEquals(listOf(DogImage("url1"), DogImage("url2")), result)
    }

    @Test
    fun `getRandomImageForBreed returns single DogImage`() = runTest {
        // Given
        whenever(api.getSingleRandomImageForBreed("boxer")).thenReturn(
           SingleImageResponse(
                message = "url123",
                status = "success"
            )
        )

        // When
        val result = repository.getRandomImageForBreed("boxer")

        // Then
        assertEquals(DogImage("url123"), result)
    }
}