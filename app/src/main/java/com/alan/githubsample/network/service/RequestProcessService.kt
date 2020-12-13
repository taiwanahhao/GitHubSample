package com.alan.githubsample.network.service

import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by AlanChang on 2020/12/12.
 */
abstract class RequestProcessService {

    private val requestProcessTransformer = SingleTransformer<Any, Any> {
        it.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                it.printStackTrace()
            }
    }

    protected fun <T> applyRequestProcess(): SingleTransformer<T, T> {
        return requestProcessTransformer as SingleTransformer<T, T>
    }

}