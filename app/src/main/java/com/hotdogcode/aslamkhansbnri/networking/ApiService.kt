package com.hotdogcode.aslamkhansbnri.networking

import com.hotdogcode.aslamkhansbnri.data.model.api.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("orgs/octokit/repos")
    suspend fun fetchUser(
        @Query("page") page: Int = 1,
        @Query("per_page") per_page: Int = 10
    ): Response<MutableList<User>>
}