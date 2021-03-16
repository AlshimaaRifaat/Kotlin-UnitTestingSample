package com.example.unittesting.ui.homePage

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.example.unittesting.R
import com.example.unittesting.api.model.SourcesItem
import com.example.unittesting.Base.BaseActivity
import com.example.unittesting.databinding.ActivityHomePageBinding
import com.example.unittesting.example.ui.homePage.NewsAdapter


class HomePageActivity : BaseActivity<HomePageViewModel, ActivityHomePageBinding>() {

    val adapter = NewsAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding.vm = viewModel
        dataBinding.content.recyclerView.adapter = adapter
        viewModel.getSources()

        subcribeToLiveData()
    }

    override fun initViewModel(): HomePageViewModel {
        return ViewModelProvider(this).get(HomePageViewModel::class.java)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home_page
    }

    private fun subcribeToLiveData() {
        viewModel.newsSourceLiveData.observe(this, Observer { data ->
            showSourcesInTabLayout(data)
        })
        viewModel.messageLiveData.observe(this, Observer {
            showMessage(title = null, message = it)
        })
        viewModel.newsListLiveData.observe(this, Observer {
            adapter.changeData(it ?: listOf())
        })
    }

    private fun showSourcesInTabLayout(sources: List<SourcesItem?>?) {
        sources?.forEach { source ->
            val tab = dataBinding.content.tablayout.newTab()
            tab.text = source?.name
            tab.tag = source
            dataBinding.content.tablayout.addTab(tab)
        }
        dataBinding.content.tablayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                val source = tab?.tag as SourcesItem
                getNewsBySourceId(source.id)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val source = tab?.tag as SourcesItem
                getNewsBySourceId(source.id)
            }
        })
        dataBinding.content.tablayout.getTabAt(0)?.select()
    }

    private fun getNewsBySourceId(id: String?) {
        viewModel.getNewsBySourceId(id)
    }
}