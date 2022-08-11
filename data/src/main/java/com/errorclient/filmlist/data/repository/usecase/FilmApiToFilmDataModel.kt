package com.errorclient.filmlist.data.repository.usecase

import com.errorclient.filmlist.data.database.models.FilmDataModel
import com.errorclient.filmlist.data.network.models.FilmDataModelApi

internal class FilmApiToFilmDataModel(private val filmApi: FilmDataModelApi) {

    fun  execute() : FilmDataModel {
        return FilmDataModel(
            title = filmApi.title,
            directorName = filmApi.directorName,
            releaseYear = filmApi.releaseYear
        )
    }
}