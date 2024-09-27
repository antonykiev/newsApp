package com.petproject.server.api

import com.petproject.server.response.EverythingResponse
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query

interface Everything {
    @GET("everything")
    suspend fun everything(
        @Query("q") keyword: String,
    ): EverythingResponse
}