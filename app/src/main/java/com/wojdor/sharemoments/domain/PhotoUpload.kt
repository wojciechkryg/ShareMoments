package com.wojdor.sharemoments.domain

data class PhotoUpload(
        val name: String,
        val longitude: Double?,
        val latitude: Double?,
        val photo: String,
        val extension: String,
        val mimetype: String
)
