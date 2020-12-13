package com.alan.githubsample.paging

import androidx.paging.PagedList

/**
 * Created by AlanChang on 2020/12/13.
 */
class PagedBoundaryCallback<T>(private val callbackPaged: IPagedBoundaryCallback) :
    PagedList.BoundaryCallback<T>() {

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        callbackPaged.isEmptyList(true)
    }

    override fun onItemAtEndLoaded(itemAtEnd: T) {
        super.onItemAtEndLoaded(itemAtEnd)
    }

    override fun onItemAtFrontLoaded(itemAtFront: T) {
        super.onItemAtFrontLoaded(itemAtFront)
        callbackPaged.isEmptyList(false)
    }
}