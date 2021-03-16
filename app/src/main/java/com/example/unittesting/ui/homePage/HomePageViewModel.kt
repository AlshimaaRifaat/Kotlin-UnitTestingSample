package com.example.unittesting.ui.homePage

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ccom.example.unittesting.repos.interfaces.SourcesOnLineDataSource

import com.example.repos.impl.SourcesRepositoryImpl
import com.example.unittesting.api.ApiManager
import com.example.unittesting.api.model.ArticlesItem
import com.example.unittesting.api.model.SourcesItem
import com.example.unittesting.repos.impl.NetworkHandler
import com.example.unittesting.repos.impl.SourcesOfflineDataSourceImpl
import com.example.unittesting.repos.impl.SourcesOnlineDataSourceImpl
import com.example.unittesting.repos.interfaces.SourcesOffLineDataSource
import com.example.unittesting.repos.interfaces.SourcesRepository

import kotlinx.coroutines.launch


class HomePageViewModel(application: Application) : AndroidViewModel(application),
    NetworkHandler {

    val newsSourceLiveData = MutableLiveData<List<SourcesItem?>?>()
    val newsListLiveData = MutableLiveData<List<ArticlesItem?>?>()
    val messageLiveData = MutableLiveData<String>()
    val showLoadingLiveData = MutableLiveData<Boolean>()
    val offlineDataSource: SourcesOffLineDataSource =
        SourcesOfflineDataSourceImpl(
            application.getSharedPreferences(
                "def",
                Context.MODE_PRIVATE
            )
        )
    val onlineDataSources: SourcesOnLineDataSource =
        SourcesOnlineDataSourceImpl(
            ApiManager.webServices()
        )
    val sourcesRepository: SourcesRepository =
        SourcesRepositoryImpl(
            offlineDataSource,
            onlineDataSources, this
        )


    override fun isOnline(): Boolean {
        return true
//        val context = getApplication<Application>()
//            .applicationContext
//        val cm =
//            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
//        val netInfo = cm?.activeNetworkInfo
//        return netInfo != null && netInfo.isConnected
    }

    fun getSources() {
        showLoadingLiveData.value = true
        viewModelScope.launch {
            try {
                val list = sourcesRepository.getSources("us")
                newsSourceLiveData.value = list
            } catch (t: Throwable) {
                Log.e("here", "fail")
                showLoadingLiveData.value = false
                messageLiveData.value = t.localizedMessage
            }
        }
    }

    fun getNewsBySourceId(id: String?) {
        showLoadingLiveData.value = true
        viewModelScope.launch {
            try {
                val response = ApiManager.webServices()
                    .getNewsBySourceId(id ?: "")
                showLoadingLiveData.value = false
                if ("ok".equals(response.status)) {
                    // adapter.changeData(response.body()?.articles ?: listOf())
                    newsListLiveData.value = response.articles
                } else {
                    messageLiveData.value = response.errorMessage
                }
            } catch (t: Throwable) {
                showLoadingLiveData.value = false
                messageLiveData.value = t.localizedMessage
//                    showMessage(title = null, message = t.localizedMessage)
            }

        }
    }
}