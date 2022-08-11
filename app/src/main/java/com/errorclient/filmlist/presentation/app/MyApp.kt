package com.errorclient.filmlist.presentation.app

import android.app.Application
import com.errorclient.filmlist.data.repository.FilmRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()

        FilmRepository.initialize(applicationContext)
    }
}