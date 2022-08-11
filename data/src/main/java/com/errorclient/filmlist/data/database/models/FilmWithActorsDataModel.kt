package com.errorclient.filmlist.data.database.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

/***
 * Витрина фильмов и связанных с ними актеров
 */
data class FilmWithActorsDataModel(
    @Embedded
    val film: FilmDataModel,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ActorFilmDataModel::class,
            parentColumn = "film_id",
            entityColumn = "actor_id"
        )
    )
    val actors: List<ActorsDataModel>
)
