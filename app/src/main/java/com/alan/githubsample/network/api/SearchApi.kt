package com.alan.githubsample.network.api

import com.alan.githubsample.network.model.SearchUserResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by AlanChang on 2020/12/11.
 */
interface SearchApi {

    @GET("/search/users")
    fun searchUser(
        @Query("q") keyword: String,
        @Query("page") page: Int
    ): Single<Response<SearchUserResponse>>
}