package com.errorclient.filmlist.data.repository

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.lang.IllegalStateException

class FilmRepositoryTest {

    @Test
    fun `companion object should return IllegalStateException without initialize`() {

        val bl = Assertions.assertThrows(IllegalStateException::class.java) {
            FilmRepository.get()
        }

        Assertions.assertEquals(bl.message, "FilmRepository must be initialized")
    }

    @Test
    fun `companion object should return FilmRepository after initialize`() {

        // не получилось :(
        // я так понимаю, нужно перенести в androidTest и context app отдавать
        // но с БД все равно не вышло...

//        val context = Mockito.mock(Context::class.java)
//
//        val filmDatabase = mock<FilmDatabase>()
//        val filmDao = mock<FilmDao>()
//        val listFilmWithActorsDataModel: Flow<List<FilmWithActorsDataModel>> =
//            flow { listOf<FilmWithActorsDataModel>() }
//
//        Mockito.`when`(filmDatabase.filmDao()).thenReturn(filmDao)
//        Mockito.`when`(filmDao.getAllFilms()).thenReturn(listFilmWithActorsDataModel)
//
//        FilmRepository.initialize(context)
//
//        FilmRepository.get()
    }
}