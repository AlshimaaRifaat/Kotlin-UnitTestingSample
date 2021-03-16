package ccom.example.unittesting.repos.interfaces

import com.example.unittesting.api.model.SourcesItem

interface SourcesOnLineDataSource {

    suspend fun getSources(country: String): List<SourcesItem>{return emptyList()}
}