package com.errorclient.filmlist.data.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://raw.githubusercontent.com"

internal object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val filmSearchApi: FilmSearchApi = retrofit.create(FilmSearchApi::class.java)
}