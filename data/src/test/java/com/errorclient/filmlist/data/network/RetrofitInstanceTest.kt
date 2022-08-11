package com.errorclient.filmlist.data.network

import com.errorclient.filmlist.data.network.models.ActorsDataModelApi
import com.errorclient.filmlist.data.network.models.FilmDataModelApi
import com.errorclient.filmlist.data.network.models.FilmListDataModelApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.AfterClass
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit

// Тест нашла тут:
// https://proandroiddev.com/testing-retrofit-converter-with-mock-webserver-50f3e1f54013

// и тоже не получилось, беда с тестами


class RetrofitInstanceTest {
//    private val mockWebServer = MockWebServer()
//
//    private val client = OkHttpClient.Builder()
//        .connectTimeout(1, TimeUnit.SECONDS)
//        .readTimeout(1, TimeUnit.SECONDS)
//        .writeTimeout(1, TimeUnit.SECONDS)
//        .build()
//
//    internal interface FilmSearchApiTest {
//
//        @GET(".")
//        suspend fun getFilmList(): Response<FilmListDataModelApi>
//    }
//
//    private val retrofit = Retrofit.Builder()
//        .baseUrl(mockWebServer.url("/"))
//        .client(client)
//        .addConverterFactory(MoshiConverterFactory.create())
//        .build()
//
//    private val api = retrofit.create(FilmSearchApiTest::class.java)
//
//    private fun MockWebServer.enqueueResponse(fileName: String, code: Int) {
//        val inputStream = javaClass.classLoader?.getResourceAsStream("raw/$fileName")
//
//        val source = inputStream?.let { inputStream.source().buffer() }
//        source?.let {
//            enqueue(
//                MockResponse()
//                    .setResponseCode(code)
//                    .setBody(source.readString(StandardCharsets.UTF_8))
//            )
//        }
//    }
//
//    @AfterClass
//    fun tearDown() {
//        mockWebServer.shutdown()
//    }
//
//    @Test
//    fun `should fetch movies correctly given 200 response`() {
//
//        mockWebServer.enqueueResponse("films.json", 200)
//
//        runBlocking {
//            val actual = api.getFilmList().body()
//
//            val film1 = FilmDataModelApi(
//                title = "Андрей Рублев",
//                directorName = "Андрей Арсеньевич Тарковский",
//                releaseYear = 1966,
//                actors = listOf(
//                    ActorsDataModelApi("Анатолий Солоницын"),
//                    ActorsDataModelApi("Иван Лапиков")
//                )
//            )
//            val film2 = FilmDataModelApi(
//                title = "Остров",
//                directorName = "Павел Семёнович Лунгин",
//                releaseYear = 2006,
//                actors = listOf(
//                    ActorsDataModelApi("Петр Мамонов"),
//                    ActorsDataModelApi("Алексей Зеленский")
//                )
//            )
//            val expected = FilmListDataModelApi(
//                items = listOf(film1, film2)
//            )
//
//            assertEquals(expected, actual)
//        }
//    }
}