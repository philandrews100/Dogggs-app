package com.p.andrews.feature

import com.p.andrews.core.coreModule
import com.p.andrews.feature.image.BreedImagesViewModel
import com.p.andrews.feature.list.BreedListViewModel
import com.p.andrews.core.domain.usecase.GetBreedImageUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    viewModel<BreedListViewModel> { BreedListViewModel(get(), get()) }
    viewModel<BreedImagesViewModel> { BreedImagesViewModel(get()) }
} + coreModule