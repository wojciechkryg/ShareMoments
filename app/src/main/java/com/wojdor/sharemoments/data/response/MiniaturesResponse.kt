package com.wojdor.sharemoments.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.wojdor.sharemoments.data.model.MiniatureModel

data class MiniaturesResponse(
        @SerializedName("_metaData") val metaData: String,
        @Expose val data: List<MiniatureModel>
)
