package com.errorclient.filmlist.data.repository.models

sealed class StatusLoading {
    object Success : StatusLoading()
    object Loading : StatusLoading()
    object Error : StatusLoading()
    object Start : StatusLoading()
}