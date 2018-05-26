package com.wojdor.sharemoments.domain.mapper

import com.wojdor.sharemoments.data.model.MetaDataModel
import com.wojdor.sharemoments.domain.MetaData

class MetaDataMapper : Mapper<MetaDataModel, MetaData> {

    override fun map(from: MetaDataModel) =
            MetaData(from.totalCount, from.page, from.currentPageCount)
}
