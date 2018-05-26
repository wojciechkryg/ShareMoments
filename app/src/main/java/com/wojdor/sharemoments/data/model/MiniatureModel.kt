package com.wojdor.sharemoments.data.model

import com.google.gson.annotations.Expose

data class MiniatureModel(
        @Expose val id: Int,
        @Expose val name: String,
        @Expose val photoMiniature: String,
        @Expose val extension: String,
        @Expose val mimetype: String
)
