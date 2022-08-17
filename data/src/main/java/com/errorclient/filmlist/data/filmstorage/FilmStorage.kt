package com.errorclient.filmlist.data.filmstorage

import com.errorclient.filmlist.data.database.models.FilmWithActorsDataModel
import kotlinx.coroutines.flow.Flow

interface FilmStorage {

    fun getAllFilms(): Flow<List<FilmWithActorsDataModel>>
    suspend fun addFilm()
}