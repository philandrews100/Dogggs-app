package com.p.andrews.core

import com.p.andrews.core.data.remote.DogApiService
import com.p.andrews.core.domain.repository.DogRepository
import com.p.andrews.core.domain.repository.DogRepositoryImpl
import com.p.andrews.core.domain.usecase.GetBreedImageUseCase
import com.p.andrews.core.domain.usecase.GetBreedImagesUseCase
import com.p.andrews.core.domain.usecase.GetBreedsUseCase
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val coreModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { GetBreedImageUseCase(get()) }
    single<DogRepository> { DogRepositoryImpl(get()) }
    single<DogApiService> { get<Retrofit>().create(DogApiService::class.java) }
    single { GetBreedsUseCase(get()) }
    single { GetBreedImagesUseCase(get()) }
}

