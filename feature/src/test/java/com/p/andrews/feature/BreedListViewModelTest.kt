package com.p.andrews.feature

import com.p.andrews.core.domain.model.DogBreed
import com.p.andrews.core.domain.model.DogImage
import com.p.andrews.core.domain.usecase.GetBreedImageUseCase
import com.p.andrews.core.domain.usecase.GetBreedsUseCase
import com.p.andrews.feature.list.BreedListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

@OptIn(ExperimentalCoroutinesApi::class)
class BreedListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val getBreedsUseCase: GetBreedsUseCase = mock()
    private val getBreedImageUseCase: GetBreedImageUseCase = mock()

    private lateinit var viewModel: BreedListViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadBreeds updates breeds list and fetches images`() = runTest {
        val breeds = listOf(DogBreed("bulldog"), DogBreed("beagle"))
        whenever(getBreedsUseCase()).thenReturn(breeds)
        whenever(getBreedImageUseCase("bulldog")).thenReturn(DogImage("url1"))
        whenever(getBreedImageUseCase("beagle")).thenReturn(DogImage("url2"))

        viewModel = BreedListViewModel(getBreedsUseCase, getBreedImageUseCase)

        advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals(2, state.breeds.size)
        assertEquals("url1", state.breeds.first { it.name == "bulldog" }.imageUrl)
        assertEquals("url2", state.breeds.first { it.name == "beagle" }.imageUrl)
        assertFalse(state.isLoading)
    }

    @Test
    fun `loadBreeds sets error on failure`() = runTest {
        whenever(getBreedsUseCase()).thenThrow(RuntimeException("network error"))

        viewModel = BreedListViewModel(getBreedsUseCase, getBreedImageUseCase)

        advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals("network error", state.error)
        assertFalse(state.isLoading)
    }

    @Test
    fun `retry clears error and reloads`() = runTest {
        whenever(getBreedsUseCase()).thenThrow(RuntimeException("initial error"))
            .thenReturn(listOf(DogBreed("terrier")))

        viewModel = BreedListViewModel(getBreedsUseCase, getBreedImageUseCase)

        advanceUntilIdle()
        assertNotNull(viewModel.state.value.error)

        viewModel.retry()
        advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals(1, state.breeds.size)
        assertEquals("terrier", state.breeds[0].name)
        assertNull(state.error)
    }

    @Test
    fun `filteredBreeds filters by query`() = runTest {
        val breeds = listOf(DogBreed("bulldog"), DogBreed("beagle"), DogBreed("boxer"))
        whenever(getBreedsUseCase()).thenReturn(breeds)

        viewModel = BreedListViewModel(getBreedsUseCase, getBreedImageUseCase)

        advanceUntilIdle()

        viewModel.searchQuery.value = "b"

        val filtered = viewModel.filteredBreeds.first { it.isNotEmpty() }

        assertEquals(3, filtered.size)
        assertTrue(filtered.any { it.name == "bulldog" })
        assertTrue(filtered.any { it.name == "boxer" })
        assertTrue(filtered.any { it.name == "beagle" })
    }
}