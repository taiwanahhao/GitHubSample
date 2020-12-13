package com.alan.githubsample.network.service

import com.alan.githubsample.network.api.SearchApi
import com.alan.githubsample.network.model.SearchUserResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Created by AlanChang on 2020/12/11.
 */
class SearchService(retrofit: Retrofit) : RequestProcessService() {
    private val api = retrofit.create(SearchApi::class.java)

    fun searchUser(keyword: String, page: Int): Single<Response<SearchUserResponse>> {
        return api.searchUser(keyword, page).compose(applyRequestProcess())
    }
}