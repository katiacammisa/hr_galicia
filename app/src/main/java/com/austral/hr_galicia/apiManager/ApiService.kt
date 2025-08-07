package com.austral.hr_galicia.apiManager

import com.austral.hr_galicia.api.RequestResponse
import retrofit.Call
import retrofit.http.GET
import retrofit.http.Query

interface ApiService {

    @GET("api")
    fun getUsers(
        @Query("seed") seed: String = "challenge",
        @Query("page") page: String,
    ): Call<RequestResponse>
}