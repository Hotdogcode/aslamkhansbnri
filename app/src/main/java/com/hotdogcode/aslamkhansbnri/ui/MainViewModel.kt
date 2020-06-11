package com.hotdogcode.aslamkhansbnri.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.hotdogcode.aslamkhansbnri.data.AppDataManager
import com.hotdogcode.aslamkhansbnri.data.datasources.NetworkDataSourceFactory
import com.hotdogcode.aslamkhansbnri.data.local.db.AppDatabase
import com.hotdogcode.aslamkhansbnri.data.model.api.User
import com.hotdogcode.aslamkhansbnri.networking.ApiClient
import com.hotdogcode.aslamkhansbnri.networking.ApiService
import kotlinx.coroutines.*

class MainViewModel(app:Application) : AndroidViewModel(app) {
    private val appDatabase = AppDatabase.getInstance(app)
    private val apiService = ApiClient.getClient().create(ApiService::class.java)
    private val dataManager = AppDataManager(apiService,appDatabase)


    val error = MutableLiveData<Boolean>(false)


    val userPaged:LiveData<PagedList<User>>
    val cacheData = MutableLiveData<List<User>>()

    val exceptionHandler= CoroutineExceptionHandler{
            coroutineContext, throwable ->throwable.printStackTrace()
    }

    val scope = CoroutineScope(Dispatchers.IO + exceptionHandler)


    init {



        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(20)
            .setEnablePlaceholders(false)
            .build()




        userPaged = initBuilder(config).build()

        //fetch2()
    }

    fun initBuilder(config: PagedList.Config): LivePagedListBuilder<Int, User>
    {

        val dataSourceFactory2 = NetworkDataSourceFactory(dataManager,scope, error)

        return LivePagedListBuilder<Int, User>(dataSourceFactory2,config)
    }



}