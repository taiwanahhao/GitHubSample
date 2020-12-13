package com.alan.githubsample.network

import com.alan.githubsample.network.core.RetrofitManager
import com.alan.githubsample.network.service.SearchService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

/**
 * Created by AlanChang on 2020/12/11.
 */
class WebServiceManager {

    val subscription = CompositeDisposable()
    val networkError = PublishSubject.create<String>()
    var searchService: SearchService

    init {
        val manager = RetrofitManager()
        val retrofit = manager.retrofit
        searchService = SearchService(retrofit)

        subscription.add(manager.networkError().subscribe {
            networkError.onNext(it)
        })
    }

    fun networkError(): Observable<String> {
        return networkError.subscribeOn(AndroidSchedulers.mainThread())
    }
}