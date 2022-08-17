package com.errorclient.filmlist.data.repository

import android.content.Context
import android.util.Log
import com.errorclient.filmlist.data.database.models.FilmWithActorsDataModel
import com.errorclient.filmlist.data.repository.models.StatusLoading
import com.errorclient.filmlist.data.filmstorage.FilmStorage
import com.errorclient.filmlist.data.repository.usecase.InternetAvailable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FilmRepository(private val context: Context, private val filmStorage: FilmStorage) {

    private val _status = MutableStateFlow<StatusLoading>(StatusLoading.Start)
    val status = _status.asStateFlow()

    fun getInternetStatus(): Boolean = InternetAvailable(context).execute()

    fun setStatus(newStatus: StatusLoading) {
        _status.value = newStatus
    }

    fun getAllFilms(): Flow<List<FilmWithActorsDataModel>> = filmStorage.getAllFilms()

    suspend fun addFilm() {

        if (!getInternetStatus()) {
            setStatus(StatusLoading.Error)
            return
        }

        if (_status.value != StatusLoading.Loading) {
            /***
             * Старт загрузки
             */
            setStatus(StatusLoading.Loading)

            try {
                filmStorage.addFilm()
                /***
                 * Загрузка успешно завершилась
                 */
                setStatus(StatusLoading.Success)

            } catch (t: Throwable) {
                Log.d("TAG", "catch")
                /***
                 * Загрузка завершилась с ошибкой
                 */
                setStatus(StatusLoading.Error)
            }
        }
    }
}