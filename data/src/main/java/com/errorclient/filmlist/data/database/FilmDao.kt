package com.errorclient.filmlist.data.database

import androidx.room.*
import com.errorclient.filmlist.data.database.models.ActorFilmDataModel
import com.errorclient.filmlist.data.database.models.ActorsDataModel
import com.errorclient.filmlist.data.database.models.FilmDataModel
import com.errorclient.filmlist.data.database.models.FilmWithActorsDataModel
import kotlinx.coroutines.flow.Flow

@Dao
internal interface FilmDao {

    @Transaction
    @Query("SELECT * FROM film_list ORDER BY year")
    fun getAllFilms(): Flow<List<FilmWithActorsDataModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addActor(actorsDataModel: ActorsDataModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFilm(filmDataModel: FilmDataModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addConnection(actorFilmDataModel: ActorFilmDataModel)
}