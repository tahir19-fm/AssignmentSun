package com.hoho.assignmentsun.networkService

import com.hoho.assignmentsun.BuildConfig
import com.hoho.assignmentsun.model.SunDetailsResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CommonApiService {
    @GET(UrlConstants.GET_SUNRISE_SUNSET)
    @Headers(
        "X-RapidAPI-Key: ${BuildConfig.API_KEY}",
        "X-RapidAPI-Host: sunrise-sunset-times.p.rapidapi.com"
    )
    suspend fun getSunData(
        @Query("date") date: String,
        @Query("latitude") lat: Double,
        @Query("longitude") lang: Double
    ): Response<SunDetailsResponseDTO>
}
