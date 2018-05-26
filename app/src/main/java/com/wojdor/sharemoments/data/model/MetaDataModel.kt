package com.wojdor.sharemoments.data.model

import com.google.gson.annotations.Expose

data class MetaDataModel(
        @Expose val totalCount: Int,
        @Expose val page: Int,
        @Expose val currentPageCount: Int
)
