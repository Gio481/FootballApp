package com.example.footballapp.data.network

import com.example.footballapp.data.model.FootballMatchDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/v2/5b9264193300006b00205fb9")
    suspend fun getFootballMatch(): Response<FootballMatchDto>
}