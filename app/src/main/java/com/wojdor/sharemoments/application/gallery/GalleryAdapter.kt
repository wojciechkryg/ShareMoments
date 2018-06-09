package com.wojdor.sharemoments.application.gallery

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.wojdor.sharemoments.R
import com.wojdor.sharemoments.application.util.inflate
import com.wojdor.sharemoments.domain.Miniature
import kotlinx.android.synthetic.main.item_miniature.view.*

class GalleryAdapter(private val onClick: (Miniature) -> Unit)
    : RecyclerView.Adapter<GalleryAdapter.MiniatureViewHolder>() {

    private var miniatures = mutableListOf<Miniature>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MiniatureViewHolder(parent.inflate(R.layout.item_miniature))

    override fun getItemCount() = miniatures.size

    override fun onBindViewHolder(holder: MiniatureViewHolder, position: Int) = holder.bind(miniatures[position], onClick)

    fun addMiniatures(newMiniatures: List<Miniature>) {
        val insertIndex = miniatures.size
        miniatures.addAll(newMiniatures)
        notifyItemInserted(insertIndex)
    }

    class MiniatureViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(miniature: Miniature, onClick: (Miniature) -> Unit) = with(view) {
            Glide.with(context)
                    .load(miniature.photoMiniatureUrl)
                    .transition(withCrossFade())
                    .into(view.itemMiniaturePhotoIv)
            setOnClickListener { onClick(miniature) }
        }
    }
}
