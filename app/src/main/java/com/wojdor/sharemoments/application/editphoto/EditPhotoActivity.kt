package com.wojdor.sharemoments.application.editphoto

import android.os.Bundle
import com.bumptech.glide.Glide
import com.wojdor.sharemoments.R
import com.wojdor.sharemoments.application.base.BaseActivity
import com.wojdor.sharemoments.application.model.Filter
import kotlinx.android.synthetic.main.activity_edit_photo.*

class EditPhotoActivity : BaseActivity(), EditPhotoContract.View {

    companion object {
        const val FILENAME_EXTRA = "com.wojdor.sharemoments.application.editphoto.FILENAME_EXTRA"
    }

    override val presenter = EditPhotoPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_photo)
        setupFiltersRv()
        presenter.onAttach()
    }

    private fun setupFiltersRv() {
        val filters = listOf<Filter>()
        // TODO: Add filters
        editPhotoFiltersRv.adapter = FiltersAdapter(filters)
        // TODO: Set LayoutManager and Snapping for RecyclerView
    }

    override fun loadTemporaryPhoto() {
        val filename = intent.extras.getString(FILENAME_EXTRA)
        try {
            openFileInput(filename).run {
                Glide.with(this@EditPhotoActivity).load(readBytes()).into(editPhotoPhotoIv)
                close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}
