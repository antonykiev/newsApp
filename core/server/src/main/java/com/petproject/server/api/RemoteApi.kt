package com.petproject.server.api

import com.petproject.server.response.EverythingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApi {

    @GET("everything")
    suspend fun everything(
        @Query("q") keyword: String = "",
        @Query("page") page: Int = 1,
    ): EverythingResponse

    @GET
    fun article(url: String): String
}

