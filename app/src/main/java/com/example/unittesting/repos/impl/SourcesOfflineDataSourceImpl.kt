package com.example.unittesting.repos.impl

import android.content.SharedPreferences
import com.example.unittesting.api.model.SourcesItem
import com.example.unittesting.repos.interfaces.SourcesOffLineDataSource


class SourcesOfflineDataSourceImpl(val sharedPref: SharedPreferences) : SourcesOffLineDataSource {

    override suspend fun cacheSources(sources: List<SourcesItem>) {
        //sharedPref.edit("sources_list",sources.toString());
        // save sources list in room db
    }

    override suspend fun getSources(): List<SourcesItem> {
        // get all sources from room db
        return listOf()
    }
}