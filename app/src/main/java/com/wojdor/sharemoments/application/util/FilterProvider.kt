package com.wojdor.sharemoments.application.util

import com.wojdor.sharemoments.R
import com.wojdor.sharemoments.application.model.Filter
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.GrayscaleTransformation
import jp.wasabeef.glide.transformations.gpu.*

class FilterProvider {

    companion object {
        const val KUWAHARA_FILTER_RADIUS = 10
    }

    val filters by lazy {
        listOf(
                Filter(R.drawable.ic_filter_invert, InvertFilterTransformation()),
                Filter(R.drawable.ic_filter_grayscale, GrayscaleTransformation()),
                Filter(R.drawable.ic_filter_sepia, SepiaFilterTransformation()),
                Filter(R.drawable.ic_filter_blur, BlurTransformation()),
                Filter(R.drawable.ic_filter_kuwahara, KuwaharaFilterTransformation(KUWAHARA_FILTER_RADIUS)),
                Filter(R.drawable.ic_filter_vignette, VignetteFilterTransformation()),
                Filter(R.drawable.ic_filter_sketch, SketchFilterTransformation()),
                Filter(R.drawable.ic_filter_toon, ToonFilterTransformation()),
                Filter(R.drawable.ic_filter_pixel, PixelationFilterTransformation()),
                Filter(R.drawable.ic_filter_swirl, SwirlFilterTransformation())
        )
    }
}
