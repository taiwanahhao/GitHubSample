package com.alan.githubsample.search.repository

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.alan.githubsample.network.WebServiceManager
import com.alan.githubsample.network.model.UserItems
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by AlanChang on 2020/12/11.
 */
class UserDateSource(private val keyword: String) : PageKeyedDataSource<Int, UserItems>(),
    KoinComponent {

    private val webServiceManager: WebServiceManager by inject()
    private var page = AtomicInteger(1)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, UserItems>
    ) {
        webServiceManager.searchService.searchUser(keyword, page.get()).subscribe({
            val list = it.body()?.items?.toMutableList() ?: mutableListOf()
            callback.onResult(list, null, page.getAndIncrement())
        }, {
            it.printStackTrace()
        })
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, UserItems>
    ) {
        val nextPage = params.key + 1
        webServiceManager.searchService.searchUser(keyword, nextPage).subscribe({
            val list = it.body()?.items?.toMutableList() ?: mutableListOf()
            callback.onResult(list, page.getAndIncrement())
        }, {
            it.printStackTrace()
        })
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, UserItems>
    ) {
    }

}

class UserDateSourceFactory(private val keyword: String) : DataSource.Factory<Int, UserItems>() {

    override fun create(): DataSource<Int, UserItems> {
        return UserDateSource(keyword)
    }
}