package com.alan.githubsample.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by AlanChang on 2020/12/11.
 */
open class BaseViewModel : ViewModel() {

    val subscription = CompositeDisposable()

    private var currentTasks = AtomicInteger(0)
    val loadingEvent = MutableLiveData<Boolean>()
    val errorEvent = MutableLiveData<String>()

    override fun onCleared() {
        super.onCleared()
        subscription.clear()
    }

    fun addTask() {
        val taskCount = currentTasks.incrementAndGet()
        showLoading(taskCount)
    }

    fun removeTask() {
        if (currentTasks.get() < 0)
            currentTasks.set(0)
        else
            currentTasks.decrementAndGet()

        val taskCount = currentTasks.get()
        showLoading(taskCount)
    }

    private fun showLoading(count: Int) {
        loadingEvent.value = count > 0
    }
}