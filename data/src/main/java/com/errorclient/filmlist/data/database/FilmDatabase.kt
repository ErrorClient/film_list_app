package com.errorclient.filmlist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.errorclient.filmlist.data.database.models.ActorFilmDataModel
import com.errorclient.filmlist.data.database.models.ActorsDataModel
import com.errorclient.filmlist.data.database.models.FilmDataModel

@Database(
    entities = [
        FilmDataModel::class,
        ActorsDataModel::class,
        ActorFilmDataModel::class
               ],
    version = 1,
    exportSchema = false
)

internal abstract class FilmDatabase : RoomDatabase() {

    abstract fun filmDao(): FilmDao
}