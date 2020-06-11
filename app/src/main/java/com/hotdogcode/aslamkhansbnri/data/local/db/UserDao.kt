package com.hotdogcode.aslamkhansbnri.data.local.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hotdogcode.aslamkhansbnri.data.model.api.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    suspend fun getAllUsers():MutableList<User>

    @Query("SELECT * FROM users")
    fun getPagedUsers(): DataSource.Factory<Int, User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<User>)
}