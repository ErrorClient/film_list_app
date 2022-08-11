package com.errorclient.filmlist.data.repository.usecase

import com.errorclient.filmlist.data.database.models.ActorsDataModel
import com.errorclient.filmlist.data.network.models.ActorsDataModelApi

internal class ActorApiToActorsDataModel(private val actorApi: ActorsDataModelApi) {

    fun execute() : ActorsDataModel {
        return ActorsDataModel(
            actorName = actorApi.actorName
        )
    }
}