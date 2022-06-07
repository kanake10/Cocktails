package com.example.remote.api

import com.example.core.Constants.ENDPOINT
import com.example.remote.dto.CockTailResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CockTailsApi {
    @GET(ENDPOINT)
    suspend fun getCockTails(
        @Query("c") cockTail: String,
    ): CockTailResponseDto


}
