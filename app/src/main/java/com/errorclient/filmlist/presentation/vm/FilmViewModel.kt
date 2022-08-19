package com.errorclient.filmlist.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.errorclient.filmlist.data.repository.FilmRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmViewModel @Inject constructor(
    private val filmRepository: FilmRepositoryInterface
    ) : ViewModel() {

    val status = filmRepository.status
    val allFilms = filmRepository.getAllFilms()
    fun addFilm() =
        viewModelScope.launch {
            filmRepository.addFilm()
        }
}