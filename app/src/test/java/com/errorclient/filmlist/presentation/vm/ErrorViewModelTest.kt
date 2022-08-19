package com.errorclient.filmlist.presentation.vm

import com.errorclient.filmlist.data.repository.FilmRepositoryInterface
import com.errorclient.filmlist.data.repository.models.StatusLoading
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.Mockito
import org.mockito.kotlin.mock

class ErrorViewModelTest {

    private lateinit var errorViewModel: ErrorViewModel
    private val filmRepository = mock<FilmRepositoryInterface>()

    @BeforeEach
    fun beforeEach() {
        errorViewModel = ErrorViewModel(filmRepository)
    }

    @AfterEach
    fun afterEach() {
        Mockito.reset(filmRepository)
    }

    @Test
    fun `setStatus should call filmRepository_setStatus one time with same status`() {

        val testNewStatus = StatusLoading.Loading

        errorViewModel.setStatus(testNewStatus)

        Mockito.verify(filmRepository, Mockito.times(1))
            .setStatus(newStatus = testNewStatus)
    }

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun `getInternetStatus should call filmRepository_getInternetStatus one time with same result`(
        testInternetStatus: Boolean
    ) {
        Mockito.`when`(filmRepository.getInternetStatus()).thenReturn(testInternetStatus)

        val actual = errorViewModel.getInternetStatus()
        val expected = testInternetStatus

        Assertions.assertEquals(expected, actual)
        Mockito.verify(filmRepository, Mockito.times(1))
            .getInternetStatus()
    }
}