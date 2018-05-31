package com.wojdor.sharemoments.data.service

import com.wojdor.sharemoments.data.model.PhotoModel
import com.wojdor.sharemoments.data.model.PhotoUploadModel
import com.wojdor.sharemoments.data.response.MiniaturesResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PhotoApi {

    @GET("/api/Photos/GetPhoto")
    fun getPhoto(@Query("id") id: Int): Single<PhotoModel>

    @GET("/api/Photos/GetPaginedMiniatures")
    fun getMiniatures(@Query("count") count: Int, @Query("page") page: Int): Single<MiniaturesResponse>

    @POST("/api/Photo/UploadPhoto")
    fun uploadPhoto(@Body photoUpload: PhotoUploadModel): Single<PhotoModel>
}
