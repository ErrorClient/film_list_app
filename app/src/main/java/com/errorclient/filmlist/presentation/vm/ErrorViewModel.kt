package com.errorclient.filmlist.presentation.vm

import androidx.lifecycle.ViewModel
import com.errorclient.filmlist.data.repository.models.StatusLoading
import com.errorclient.filmlist.data.repository.FilmRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ErrorViewModel @Inject constructor(private val filmRepository: FilmRepositoryInterface)
    : ViewModel() {

    /***
     * Если были ошибки загрузки или отсутствовал интернет,
     * у фрагмента InternetFragment должна быть возможность инициировать загрузку
     */
    fun setStatus(newStatus: StatusLoading) = filmRepository.setStatus(newStatus)
    fun getInternetStatus() : Boolean = filmRepository.getInternetStatus()
}