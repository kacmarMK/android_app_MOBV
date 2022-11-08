package com.example.network

import com.example.data.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

private const val BASE_URL = "https://data.mongodb-api.com/app/data-fswjp/endpoint/data/v1/action/find"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface PubsApiService {
    @Headers("Content-Type: application/json")
    @POST("?api-key=KHUu1Fo8042UwzczKz9nNeuVOsg2T4ClIfhndD2Su0G0LHHCBf0LnUF05L231J0M")
    suspend fun getAllPubs(@Body requestBody: RequestBody?): String
}

object PubsApi {
    val retrofitService: PubsApiService by lazy {
        retrofit.create(PubsApiService::class.java)
    }
}