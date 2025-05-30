package com.p.andrews.core.data.remote

import com.p.andrews.core.data.model.BreedResponse
import com.p.andrews.core.data.model.ImageResponse
import com.p.andrews.core.data.model.SingleImageResponse
import com.p.andrews.core.domain.model.DogImage
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApiService {
    @GET("breeds/list/all")
    suspend fun getAllBreeds(): BreedResponse

    @GET("breed/{breed}/images/random/100")
    suspend fun getImagesForBreed(@Path("breed") breed: String): ImageResponse

    // ✅ Add this:
    @GET("breed/{breed}/images/random")
    suspend fun getSingleRandomImageForBreed(@Path("breed") breed: String): SingleImageResponse
}