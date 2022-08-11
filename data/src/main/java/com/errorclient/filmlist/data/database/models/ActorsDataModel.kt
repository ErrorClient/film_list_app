package com.errorclient.filmlist.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/***
 * Таблица с актерами
 */
@Entity(tableName = "actors_list")
data class ActorsDataModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val actorName: String
    )
