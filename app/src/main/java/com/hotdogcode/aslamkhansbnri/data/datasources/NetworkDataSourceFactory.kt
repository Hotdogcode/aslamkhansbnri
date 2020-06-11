package com.hotdogcode.aslamkhansbnri.data.datasources

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.hotdogcode.aslamkhansbnri.data.AppDataManager
import com.hotdogcode.aslamkhansbnri.data.model.api.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

class NetworkDataSourceFactory(
    private val dataManager: AppDataManager,
    private val coroutineScope: CoroutineScope,
    private val error:MutableLiveData<Boolean>
): DataSource.Factory<Int,User> ()
{

    val newsDataSourceLiveData = MutableLiveData<NetworkDataSource>()
    override fun create(): DataSource<Int, User> {
        val newsDataSource = NetworkDataSource(coroutineScope, dataManager, error)
        newsDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}