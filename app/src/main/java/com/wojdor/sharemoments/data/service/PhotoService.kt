package com.wojdor.sharemoments.data.service

import com.wojdor.sharemoments.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object PhotoService {

    private val client by lazy {
        val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(BuildConfig.SHARE_MOMENTS_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    val instance: PhotoApi by lazy { retrofit.create(PhotoApi::class.java) }
}
