package com.errorclient.filmlist.data.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

internal class RetrofitInstance(baseUrl: String) {
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val filmSearchApi: FilmSearchApi = retrofit.create(FilmSearchApi::class.java)
}