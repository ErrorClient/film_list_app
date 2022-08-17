package com.errorclient.filmlist.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity

/***
 * Таблица-связка фильма и актера
 */
@Entity(
    tableName = "film_actor",
    primaryKeys = ["film_id", "actor_id"]
)
data class ActorFilmDataModel(

    @ColumnInfo(name = "film_id")
    val filmId: String,

    @ColumnInfo(name = "actor_id", index = true)
    val actorId: String
)
