package com.errorclient.filmlist.presentation.di

import com.errorclient.filmlist.data.repository.FilmRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HiltDataModule {

    @Provides
    @Singleton
    fun provideFilmRepository() : FilmRepository {
        return FilmRepository.get()
    }
}