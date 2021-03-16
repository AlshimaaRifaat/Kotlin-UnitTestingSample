package com.example.unittesting.repos.interfaces

import com.example.unittesting.api.model.SourcesItem

interface SourcesRepository {

    suspend fun getSources(countryId: String): List<SourcesItem>
}