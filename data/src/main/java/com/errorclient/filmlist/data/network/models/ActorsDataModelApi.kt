package com.errorclient.filmlist.data.network.models

import com.squareup.moshi.Json

/***
 * Как парсить актеров
 */
data class ActorsDataModelApi(
    @Json(name = "actorName") val actorName: String
)
