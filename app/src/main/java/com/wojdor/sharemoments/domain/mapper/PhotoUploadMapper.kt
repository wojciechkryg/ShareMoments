package com.wojdor.sharemoments.domain.mapper

import com.wojdor.sharemoments.data.model.PhotoUploadModel
import com.wojdor.sharemoments.domain.PhotoUpload

class PhotoUploadMapper : Mapper<PhotoUpload, PhotoUploadModel> {

    override fun map(from: PhotoUpload) = PhotoUploadModel(
            from.name, from.longitude.toString(), from.latitude.toString(),
            from.photo, from.extension, from.mimetype
    )
}
