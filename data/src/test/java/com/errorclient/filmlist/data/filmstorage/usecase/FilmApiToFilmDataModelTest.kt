package com.errorclient.filmlist.data.filmstorage.usecase

import com.errorclient.filmlist.data.database.models.FilmDataModel
import com.errorclient.filmlist.data.network.models.ActorsDataModelApi
import com.errorclient.filmlist.data.network.models.FilmDataModelApi
import com.errorclient.filmlist.data.filmstorage.usecase.FilmApiToFilmDataModel
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class FilmApiToFilmDataModelTest {

    @Test
    fun `should return FilmDataModel object with same data`() {

        val testTitle = "Test title"
        val testDirectorName = "Test directorName"
        val testReleaseYear = 2022
        val testActors: List<ActorsDataModelApi> = listOf()

        val filmApiTest = FilmDataModelApi(
            title = testTitle,
            directorName = testDirectorName,
            releaseYear = testReleaseYear,
            actors = testActors
        )

        val actual = FilmApiToFilmDataModel(filmApiTest).execute()
        val expected = FilmDataModel(
            title = testTitle,
            directorName = testDirectorName,
            releaseYear = testReleaseYear
        )

        Assertions.assertEquals(expected, actual)
    }
}