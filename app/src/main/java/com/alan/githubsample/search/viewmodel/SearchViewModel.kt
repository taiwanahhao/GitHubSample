package com.alan.githubsample.search.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.alan.githubsample.base.BaseViewModel
import com.alan.githubsample.network.model.UserItems
import com.alan.githubsample.paging.IPagedBoundaryCallback
import com.alan.githubsample.search.repository.ISearchRepository

/**
 * Created by AlanChang on 2020/12/10.
 */
class SearchViewModel(private val repository: ISearchRepository) : BaseViewModel() {

    val usersEvent = MutableLiveData<PagedList<UserItems>>()

    fun search(keyword: String, callbackPaged: IPagedBoundaryCallback) {
        addTask()
        subscription.add(repository.searchUser(keyword, callbackPaged).subscribe({
            removeTask()
            usersEvent.value = it
        }, {
            removeTask()
            it.printStackTrace()
            errorEvent.value = it.message
        }))
    }
}