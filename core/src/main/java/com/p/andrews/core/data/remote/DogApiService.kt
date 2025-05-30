package com.p.andrews.core.data.remote

import com.p.andrews.core.data.model.BreedResponse
import com.p.andrews.core.data.model.ImageResponse
import com.p.andrews.core.data.model.SingleImageResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApiService {
    @GET("breeds/list/all")
    suspend fun getAllBreeds(): BreedResponse

    @GET("breed/{breedPath}/images/random/10")
    suspend fun getImagesForBreed(@Path("breedPath", encoded = true) breedPath: String): ImageResponse

    @GET("breed/{breedPath}/images/random")
    suspend fun getSingleRandomImageForBreed(@Path("breedPath", encoded = true) breedPath: String): SingleImageResponse
}