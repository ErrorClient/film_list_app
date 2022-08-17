package com.errorclient.filmlist.data.network

import com.errorclient.filmlist.data.network.models.FilmListDataModelApi
import retrofit2.Response
import retrofit2.http.GET

internal interface FilmSearchApi {

    @GET("films.json")
    suspend fun getFilmList(): Response<FilmListDataModelApi>
}