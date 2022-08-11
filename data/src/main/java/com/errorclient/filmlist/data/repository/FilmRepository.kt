package com.errorclient.filmlist.data.repository

import android.content.Context
import androidx.room.Room
import com.errorclient.filmlist.data.database.FilmDatabase
import com.errorclient.filmlist.data.database.models.ActorFilmDataModel
import com.errorclient.filmlist.data.database.models.FilmWithActorsDataModel
import com.errorclient.filmlist.data.network.RetrofitInstance
import com.errorclient.filmlist.data.repository.usecase.ActorApiToActorsDataModel
import com.errorclient.filmlist.data.repository.usecase.FilmApiToFilmDataModel
import com.errorclient.filmlist.data.repository.models.StatusLoading
import com.errorclient.filmlist.data.repository.usecase.InternetAvailable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking
import java.lang.IllegalStateException
import java.util.concurrent.Executors

private const val DATABASE_NAME = "film_database"

class FilmRepository(private val context: Context) {

    private val database: FilmDatabase =
        Room.databaseBuilder(
            context,
            FilmDatabase::class.java,
            DATABASE_NAME
        ).build()

    private val filmDao = database.filmDao()
    private val executor = Executors.newSingleThreadExecutor()

    private val _status = MutableStateFlow<StatusLoading>(StatusLoading.Start)
    val status = _status.asStateFlow()

    fun getInternetStatus() : Boolean = InternetAvailable(context).execute()

    fun setStatus(newStatus: StatusLoading) {
        _status.value = newStatus
    }

    fun getAllFilms(): Flow<List<FilmWithActorsDataModel>> = filmDao.getAllFilms()

    fun addFilm() {

        if ( !getInternetStatus() ) {
            setStatus(StatusLoading.Error)
            return
        }

        if (_status.value != StatusLoading.Loading) {

            executor.execute {
                runBlocking {
                    /***
                     * Старт загрузки
                     */
                    setStatus(StatusLoading.Loading)

                    try {
                        val response = RetrofitInstance.filmSearchApi.getFilmList()

                        if (response.isSuccessful) {

                            /***
                             * Берем список всех фильмов в ответе.
                             * Для каждого фильма:
                             * парсим и добавляем в БД
                             */
                            val filmListApi = response.body()?.items ?: listOf()

                            for (filmApi in filmListApi) {

                                val film = FilmApiToFilmDataModel(filmApi).execute()

                                filmDao.addFilm(film)

                                /***
                                 * Берем список всех актеров для рассматриваемого фильма.
                                 * Для каждого актера:
                                 * парсим
                                 * добавляем в БД актера и связь с фильмом
                                 */
                                val actorsListApi = filmApi.actors

                                for (actorApi in actorsListApi) {

                                    val actor = ActorApiToActorsDataModel(actorApi).execute()
                                    val connection = ActorFilmDataModel(film.title, actor.actorName)

                                    filmDao.addActor(actor)
                                    filmDao.addConnection(connection)
                                }
                            }
                        }

                        /***
                         * Загрузка успешно завершилась
                         */
                        setStatus(StatusLoading.Success)

                    } catch (t: Throwable) {
                        /***
                         * Загрузка завершилась с ошибкой
                         */
                        setStatus(StatusLoading.Error)
                    }
                }
            }
        }
    }

    companion object {

        private var INSTANCE: FilmRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) INSTANCE = FilmRepository(context = context)
        }

        fun get(): FilmRepository {
            return INSTANCE ?: throw IllegalStateException("FilmRepository must be initialized")
        }
    }
}