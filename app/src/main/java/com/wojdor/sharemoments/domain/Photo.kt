package com.wojdor.sharemoments.domain

data class Photo(
        val id: Int,
        val name: String,
        val longitude: Double?,
        val latitude: Double?,
        val photoUrl: String,
        val photoMiniatureUrl: String,
        val extension: String,
        val mimetype: String
)
