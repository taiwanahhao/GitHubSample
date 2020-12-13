package com.alan.githubsample.network.core

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by AlanChang on 2020/12/11.
 */
class RetrofitManager {

    val retrofit: Retrofit
    private val networkError = PublishSubject.create<String>()

    init {
        val client = createOkHttpClient()
        retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiConfig.WEB_HOST)
            .client(client)
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .method(original.method(), original.body())
                    .build()
                val response = chain.proceed(request)

                if (response.code() != 200)
                    networkError.onNext(response.message())

                response
            }
            .connectTimeout(ApiConfig.TIME_OUT_CONNECT.toLong(), TimeUnit.SECONDS)
            .readTimeout(ApiConfig.TIME_OUT_READ.toLong(), TimeUnit.SECONDS)
            .writeTimeout(ApiConfig.TIME_OUT_WRITE.toLong(), TimeUnit.SECONDS)
            .build()
    }

    fun networkError(): Observable<String> {
        return networkError.hide()
    }
}