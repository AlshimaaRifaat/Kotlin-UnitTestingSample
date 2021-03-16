package com.example.repos.impl


import ccom.example.unittesting.repos.interfaces.SourcesOnLineDataSource
import com.example.unittesting.api.model.SourcesItem
import com.example.unittesting.repos.impl.NetworkHandler


import com.example.unittesting.repos.interfaces.SourcesOffLineDataSource

import com.example.unittesting.repos.interfaces.SourcesRepository

typealias Sources = List<SourcesItem>

class SourcesRepositoryImpl(
    val offlineDataSource: SourcesOffLineDataSource,
    val onlineDataSource: SourcesOnLineDataSource,
    val networkHandler: NetworkHandler
) : SourcesRepository {

    override suspend fun getSources(countryId: String): Sources {
        var sourcesList: List<SourcesItem>
        if (networkHandler.isOnline()) {
            sourcesList = onlineDataSource.getSources(countryId)
           // offlineDataSource.cacheSources(sourcesList)
        } else {
            sourcesList = offlineDataSource.getSources()
        }

        return sourcesList
    }
}