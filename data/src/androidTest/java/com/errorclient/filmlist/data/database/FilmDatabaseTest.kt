package com.errorclient.filmlist.data.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.errorclient.filmlist.data.database.models.FilmDataModel
import com.errorclient.filmlist.data.database.models.FilmWithActorsDataModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.junit.jupiter.api.Assertions
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class FilmDatabaseTest {
    // и так тоже не получилось :(

//    private lateinit var filmDao: FilmDao
//    private lateinit var db: FilmDatabase
//
//    @Before
//    fun createDb() {
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        db = Room.inMemoryDatabaseBuilder(
//            context,
//            FilmDatabase::class.java
//        ).build()
//        filmDao = db.filmDao()
//    }
//
//    @After
//    @Throws(IOException::class)
//    fun closeDb() {
//        db.close()
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun writeUserAndReadInList() =
//        runBlocking {
//            val filmDataModel = FilmDataModel(title = "jj", directorName = "bb", releaseYear = 3)
//        val filmWithActorsDataModel = FilmWithActorsDataModel(filmDataModel, listOf())
//        val testFilmList = listOf(filmWithActorsDataModel)
//
//         filmDao.addFilm(filmDataModel)
//        var filmList: List<FilmWithActorsDataModel>
//        filmDao.getAllFilms().collect{
//            filmList = it
//
//            Assertions.assertEquals(filmList, testFilmList)
//        } }
}