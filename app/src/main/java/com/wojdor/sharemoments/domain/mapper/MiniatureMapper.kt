package com.wojdor.sharemoments.domain.mapper

import com.wojdor.sharemoments.data.model.MiniatureModel
import com.wojdor.sharemoments.domain.Miniature

class MiniatureMapper : Mapper<MiniatureModel, Miniature> {

    override fun map(from: MiniatureModel) =
            Miniature(from.id, from.name, from.photoMiniature, from.extension, from.mimetype)
}
