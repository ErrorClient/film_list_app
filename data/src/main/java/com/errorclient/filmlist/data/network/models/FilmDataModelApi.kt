package com.errorclient.filmlist.data.network.models

import com.squareup.moshi.Json

/***
 * Как парсить фильмы
 */
data class FilmDataModelApi(
    @Json(name = "title") val title: String,
    @Json(name = "directorName") val directorName: String,
    @Json(name = "releaseYear") val releaseYear: Int,
    @Json(name = "actors") val actors: List<ActorsDataModelApi>
)
