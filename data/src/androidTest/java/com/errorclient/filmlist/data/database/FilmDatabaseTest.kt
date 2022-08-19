package com.errorclient.filmlist.data.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.errorclient.filmlist.data.database.models.ActorFilmDataModel
import com.errorclient.filmlist.data.database.models.ActorsDataModel
import com.errorclient.filmlist.data.database.models.FilmDataModel
import com.errorclient.filmlist.data.database.models.FilmWithActorsDataModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class FilmDatabaseTest {

    private lateinit var filmDatabase: FilmDatabase
    private lateinit var filmDao: FilmDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        filmDatabase = Room.inMemoryDatabaseBuilder(context, FilmDatabase::class.java).build()
        filmDao = filmDatabase.filmDao()
    }

    @After
    fun closeDb() {
        filmDatabase.close()
    }

    @Test
    fun addDataAndGet() = runBlocking{
        val actorName = "Test Name"
        val title = "Test Title"
        val directorName = "Test Director Name"
        val releaseYear = 2022

        val actorsDataModel = ActorsDataModel(actorName = actorName)
        val filmDataModel = FilmDataModel(
            title = title, directorName = directorName, releaseYear = releaseYear
        )
        val actorFilmDataModel = ActorFilmDataModel(filmId = title, actorId = actorName)
        val expected = FilmWithActorsDataModel(
            film = filmDataModel, actors = listOf(actorsDataModel)
        )

        filmDao.addActor(actorsDataModel)
        filmDao.addFilm(filmDataModel)
        filmDao.addConnection(actorFilmDataModel)

        val allFilmFlow = filmDao.getAllFilms().first()
        val actual = allFilmFlow.first()

        assertEquals(expected, actual)
    }
}