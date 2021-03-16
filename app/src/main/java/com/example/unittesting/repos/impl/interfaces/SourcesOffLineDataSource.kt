package com.example.unittesting.repos.interfaces

import com.example.unittesting.api.model.SourcesItem


public interface SourcesOffLineDataSource {

    suspend fun cacheSources(sources: List<SourcesItem>)
    suspend fun getSources(): List<SourcesItem>{return emptyList()}

}