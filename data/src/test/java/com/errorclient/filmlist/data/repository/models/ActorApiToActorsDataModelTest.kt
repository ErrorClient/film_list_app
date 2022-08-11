package com.errorclient.filmlist.data.repository.models

import com.errorclient.filmlist.data.database.models.ActorsDataModel
import com.errorclient.filmlist.data.network.models.ActorsDataModelApi
import com.errorclient.filmlist.data.repository.usecase.ActorApiToActorsDataModel
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ActorApiToActorsDataModelTest {

    @Test
    fun `should return ActorsDataModel object with same actorName`() {

        val testName = "Test Name"
        val actorApiTest = ActorsDataModelApi(actorName = testName)

        val actual = ActorApiToActorsDataModel(actorApiTest).execute()
        val expected = ActorsDataModel(actorName = testName)

        Assertions.assertEquals(expected, actual)
    }
}