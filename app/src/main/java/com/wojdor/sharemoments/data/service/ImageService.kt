package com.wojdor.sharemoments.data.service

import com.wojdor.sharemoments.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ImageService {

    private val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(BuildConfig.SHARE_MOMENTS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    val instance by lazy { retrofit.create(ImageApi::class.java) }
}
