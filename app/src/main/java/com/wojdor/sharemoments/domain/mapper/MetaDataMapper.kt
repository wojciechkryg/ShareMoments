package com.wojdor.sharemoments.domain.mapper

import com.google.gson.Gson
import com.wojdor.sharemoments.domain.MetaData

class MetaDataMapper : Mapper<String, MetaData> {

    override fun map(from: String) =
            Gson().fromJson(from, MetaData::class.java)
}
