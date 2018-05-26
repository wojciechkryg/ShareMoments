package com.wojdor.sharemoments.domain.mapper

import com.wojdor.sharemoments.data.model.PhotoModel
import com.wojdor.sharemoments.domain.Photo

class PhotoMapper : Mapper<PhotoModel, Photo> {

    override fun map(from: PhotoModel) = Photo(
            from.id, from.name, from.longitude.toDoubleOrNull(), from.latitude.toDoubleOrNull(),
            from.photo, from.photoMiniature, from.extension, from.mimetype
    )
}
