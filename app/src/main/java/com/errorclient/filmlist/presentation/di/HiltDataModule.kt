package com.errorclient.filmlist.presentation.di

import android.content.Context
import com.errorclient.filmlist.data.filmstorage.FilmStorage
import com.errorclient.filmlist.data.filmstorage.russianfilm.RussianFilmStorage
import com.errorclient.filmlist.data.repository.FilmRepository
import com.errorclient.filmlist.data.repository.FilmRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HiltDataModule {

    @Provides
    @Singleton
    fun provideFilmRepository(
        @ApplicationContext context: Context,
        filmStorage: FilmStorage
    ): FilmRepositoryInterface {
        return FilmRepository(context, filmStorage)
    }

    @Provides
    @Singleton
    fun provideFilmStorage(@ApplicationContext context: Context): FilmStorage {
        return RussianFilmStorage(context)
    }
}