package com.errorclient.filmlist.data.repository

import com.errorclient.filmlist.data.database.models.FilmWithActorsDataModel
import com.errorclient.filmlist.data.repository.models.StatusLoading
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface FilmRepositoryInterface {
    val status: StateFlow<StatusLoading>
    fun getInternetStatus(): Boolean
    fun setStatus(newStatus: StatusLoading)
    fun getAllFilms(): Flow<List<FilmWithActorsDataModel>>
    suspend fun addFilm()
}