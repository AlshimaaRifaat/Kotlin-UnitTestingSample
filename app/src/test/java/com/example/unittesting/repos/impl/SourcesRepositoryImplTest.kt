package com.example.unittesting.repos.impl


import ccom.example.unittesting.repos.interfaces.SourcesOnLineDataSource
import com.example.repos.impl.SourcesRepositoryImpl
import com.example.unittesting.api.model.SourcesItem
import com.example.unittesting.repos.interfaces.SourcesOffLineDataSource
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test


class SourcesRepositoryImplTest {

    @Test
    fun `getSources() with online network then get list of sources from online data source`(){
        runBlocking {
            //arrange
            val onlineDataSource=object: SourcesOnLineDataSource {
                override suspend fun getSources(country: String): List<SourcesItem> {
                    return listOf(SourcesItem(id = "0",name = "Shimaa"))
                }
            }

            val sourcesRepositoryImpl=SourcesRepositoryImpl(object: SourcesOffLineDataSource{
                override suspend fun cacheSources(sources: List<SourcesItem>) {
                    TODO("Not yet implemented")
                }
            },
                onlineDataSource,object: NetworkHandler{})


            //act
            val result=sourcesRepositoryImpl.getSources("0")

            //assert
            val expected=listOf(SourcesItem(id = "0",name = "Shimaa"))

            assertEquals(expected,result) }
    }

    @Test
    fun `getSources() with online network verify get list of sources from network called`(){
        runBlocking {
            //arrange
            var getSourcesInvoked=false
            val onlineDataSource=object: SourcesOnLineDataSource {
                override suspend fun getSources(country: String): List<SourcesItem> {
                    getSourcesInvoked=true
                    return listOf()
                }
            }

            val sourcesRepositoryImpl= SourcesRepositoryImpl(object: SourcesOffLineDataSource {
                override suspend fun cacheSources(sources: List<SourcesItem>) {
                    TODO("Not yet implemented")
                }
            },
                onlineDataSource,object: NetworkHandler{})


            //act
            val result=sourcesRepositoryImpl.getSources("0")

            //assert

            assertEquals(getSourcesInvoked,true) }
    }

}