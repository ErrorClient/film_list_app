package com.errorclient.filmlist.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.errorclient.filmlist.data.repository.FilmRepository
import com.errorclient.filmlist.data.repository.models.StatusLoading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmViewModel @Inject constructor(private val filmRepository: FilmRepository)
    : ViewModel() {

    val status = filmRepository.status
    val allFilms = filmRepository.getAllFilms()
    fun addFilm() = filmRepository.addFilm()
}