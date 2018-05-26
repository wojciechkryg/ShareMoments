package com.wojdor.sharemoments.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PhotoModel(
        @Expose val id: Int,
        @Expose val name: String,
        @Expose val longitude: String,
        @SerializedName("altitude") val latitude: String,
        @Expose val photo: String,
        @Expose val photoMiniature: String,
        @Expose val extension: String,
        @Expose val mimetype: String
)
