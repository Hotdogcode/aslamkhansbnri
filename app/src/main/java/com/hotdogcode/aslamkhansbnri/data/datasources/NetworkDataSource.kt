package com.hotdogcode.aslamkhansbnri.data.datasources

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.hotdogcode.aslamkhansbnri.data.AppDataManager
import com.hotdogcode.aslamkhansbnri.data.model.api.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception

class NetworkDataSource(
    private val scope:CoroutineScope,
    private val dataManager: AppDataManager,
    private val error:MutableLiveData<Boolean>
):PageKeyedDataSource<Int,User>() {

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {

    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, User>
    ) {
        scope.launch {
            try {
                val response = dataManager.fetchUsers(1, 10)

                when {
                    response.isSuccessful -> {
                        val userList = response.body()
                        if(userList==null || userList.isEmpty()){
                            val cachedData = dataManager.getAllUsers()
                            if(cachedData.size>0){
                                callback.onResult(cachedData,1,2)
                            }
                        }else{
                            dataManager.insertAll(userList)
                            callback.onResult(userList, 1, 2)
                        }
                    }
                    else -> {
                        val cachedData = dataManager.getAllUsers()
                        if(cachedData.size>0){
                            callback.onResult(cachedData,1,2)
                        }
                    }
                }
            }catch (e:Exception){
                val cachedData = dataManager.getAllUsers()
                if(cachedData.size>0){
                    callback.onResult(cachedData,1,2)
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        scope.launch {
            val response = dataManager.fetchUsers(params.key,10)
            when{
                response.isSuccessful->{
                    val userList = response.body()
                    userList?.let {
                        dataManager.insertAll(it)
                        callback.onResult(it,params.key+1)
                    }
                }
                else->{
                    Log.e(javaClass.name,response.message())
                }
            }
        }
    }
}