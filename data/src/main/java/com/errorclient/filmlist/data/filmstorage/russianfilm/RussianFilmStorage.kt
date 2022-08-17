package com.errorclient.filmlist.data.filmstorage.russianfilm

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.errorclient.filmlist.data.database.FilmDatabase
import com.errorclient.filmlist.data.database.models.ActorFilmDataModel
import com.errorclient.filmlist.data.database.models.FilmWithActorsDataModel
import com.errorclient.filmlist.data.filmstorage.FilmStorage
import com.errorclient.filmlist.data.network.RetrofitInstance
import com.errorclient.filmlist.data.filmstorage.usecase.ActorApiToActorsDataModel
import com.errorclient.filmlist.data.filmstorage.usecase.FilmApiToFilmDataModel
import kotlinx.coroutines.flow.Flow

private const val DATABASE_NAME = "russian_film_database"
private const val BASE_URL =
    "https://raw.githubusercontent.com/constanta-android-dev/intership-wellcome-task/main/"

class RussianFilmStorage(context: Context) : FilmStorage {

    private val database: FilmDatabase =
        Room.databaseBuilder(
            context,
            FilmDatabase::class.java,
            DATABASE_NAME
        ).build()

    private val filmDao = database.filmDao()

    override fun getAllFilms(): Flow<List<FilmWithActorsDataModel>> = filmDao.getAllFilms()

    override suspend fun addFilm() {

        Log.d("TAG", "addFilm")
        val response = RetrofitInstance(BASE_URL).filmSearchApi.getFilmList()

        if (response.isSuccessful) {
            Log.d("TAG", "addFilm isSuccessful")

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
    }
}