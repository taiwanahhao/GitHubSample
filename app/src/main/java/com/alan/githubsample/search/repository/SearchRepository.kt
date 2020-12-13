package com.alan.githubsample.search.repository

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.alan.githubsample.network.model.UserItems
import com.alan.githubsample.paging.IPagedBoundaryCallback
import com.alan.githubsample.paging.PagedBoundaryCallback
import io.reactivex.Observable

/**
 * Created by AlanChang on 2020/12/11.
 */
interface ISearchRepository {
    fun searchUser(keyword: String, callback: IPagedBoundaryCallback): Observable<PagedList<UserItems>>
}

class SearchRepository : ISearchRepository {

    override fun searchUser(keyword: String, callback: IPagedBoundaryCallback): Observable<PagedList<UserItems>> {
        val factory = UserDateSourceFactory(keyword)
        val pagedListConfig = PagedList.Config.Builder()
            .setPrefetchDistance(5)
            .build()

        return RxPagedListBuilder(factory, pagedListConfig)
            .setBoundaryCallback(PagedBoundaryCallback<UserItems>(callback))
            .buildObservable()
    }
}