package com.example.unittesting.repos.impl


import ccom.example.unittesting.repos.interfaces.SourcesOnLineDataSource
import com.example.unittesting.api.WebServices
import com.example.unittesting.api.model.SourcesItem


class SourcesOnlineDataSourceImpl(val webServices: WebServices) :
    SourcesOnLineDataSource {
    override suspend fun getSources(country: String): List<SourcesItem> {
        val response = webServices.getSources(country)
        return response.sources ?: listOf()
    }
}