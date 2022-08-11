package com.errorclient.filmlist.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/***
 * Таблица с фильмами
 */
@Entity(tableName = "film_list")
data class FilmDataModel (
    @PrimaryKey
    @ColumnInfo(name = "id")
    val title: String,

    @ColumnInfo(name = "director")
    val directorName: String,

    @ColumnInfo(name = "year")
    val releaseYear: Int?
)