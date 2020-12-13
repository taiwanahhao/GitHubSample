package com.alan.githubsample

import android.app.Application
import android.widget.Toast
import com.alan.githubsample.network.WebServiceManager
import com.alan.githubsample.search.repository.SearchRepository
import com.alan.githubsample.search.viewmodel.SearchViewModel
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

/**
 * Created by AlanChang on 2020/12/11.
 */
class App : Application() {

    private val webServiceManager: WebServiceManager by inject()

    private val appModule = module {
        single { WebServiceManager() }

        viewModel {
            SearchViewModel(
                SearchRepository()
            )
        }
    }

    private fun init() {
        webServiceManager.networkError().subscribe {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }

        init()
    }
}