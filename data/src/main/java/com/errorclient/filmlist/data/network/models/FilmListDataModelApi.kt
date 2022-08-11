package com.errorclient.filmlist.data.network.models

import com.squareup.moshi.Json

/***
 * Как парсить ответ
 */
data class FilmListDataModelApi (
    @Json(name = "items") val items: List<FilmDataModelApi>
)