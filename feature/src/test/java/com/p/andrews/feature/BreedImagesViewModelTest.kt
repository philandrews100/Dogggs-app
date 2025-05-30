package com.p.andrews.feature

import com.p.andrews.core.domain.model.DogImage
import com.p.andrews.core.domain.repository.DogRepository
import com.p.andrews.feature.image.BreedImagesUiState
import com.p.andrews.feature.image.BreedImagesViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*
import kotlin.test.assertIs

@OptIn(ExperimentalCoroutinesApi::class)
class BreedImagesViewModelTest {

    private val dispatcher = StandardTestDispatcher()
    private lateinit var repository: DogRepository
    private lateinit var viewModel: BreedImagesViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        repository = mock()
        viewModel = BreedImagesViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state is Loading`() {
        assertIs<BreedImagesUiState.Loading>(viewModel.uiState.value)
    }

    @Test
    fun `loadImages sets Success state on success`() = runTest {
        // Given
        val breed = "beagle"
        val images = listOf(DogImage("url1"), DogImage("url2"))
        whenever(repository.getImagesForBreed(breed)).thenReturn(images)

        // When
        viewModel.loadImages(breed)
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assertIs<BreedImagesUiState.Success>(state)
        assertEquals(images, state.images)
    }

    @Test
    fun `loadImages sets Error state on exception`() = runTest {
        // Given
        val breed = "unknown"
        whenever(repository.getImagesForBreed(breed)).thenThrow(RuntimeException("Network error"))

        // When
        viewModel.loadImages(breed)
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assertIs<BreedImagesUiState.Error>(state)
        assertEquals("Failed to load images", state.message)
    }
}