package com.p.andrews.core

import com.p.andrews.core.domain.model.DogBreed
import com.p.andrews.core.domain.repository.DogRepository
import com.p.andrews.core.domain.usecase.GetBreedsUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*
import kotlin.test.assertEquals

class GetBreedsUseCaseTest {

    private lateinit var repository: DogRepository
    private lateinit var useCase: GetBreedsUseCase

    @Before
    fun setUp() {
        repository = mock()
        useCase = GetBreedsUseCase(repository)
    }

    @Test
    fun `invoke returns list of dog breeds`() = runTest {
        // Given
        val expectedBreeds = listOf(
            DogBreed("beagle"),
            DogBreed("bulldog"),
            DogBreed("retriever")
        )
        whenever(repository.getAllBreeds()).thenReturn(expectedBreeds)

        // When
        val result = useCase()

        // Then
        assertEquals(expectedBreeds, result)
    }
}