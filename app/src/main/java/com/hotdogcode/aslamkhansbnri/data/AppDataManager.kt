package com.hotdogcode.aslamkhansbnri.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hotdogcode.aslamkhansbnri.data.local.db.AppDatabase
import com.hotdogcode.aslamkhansbnri.data.model.api.User
import com.hotdogcode.aslamkhansbnri.networking.ApiClient
import com.hotdogcode.aslamkhansbnri.networking.ApiService

class AppDataManager(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) {

    suspend fun fetchUsers(page:Int, pageSize:Int) = apiService.fetchUser(page,pageSize)

    suspend fun getAllUsers() = appDatabase.userDao().getAllUsers()

    fun getPagedUsers()=appDatabase.userDao().getPagedUsers()

    suspend fun insertAll(users: List<User>)=appDatabase.userDao().insertAll(users)

}