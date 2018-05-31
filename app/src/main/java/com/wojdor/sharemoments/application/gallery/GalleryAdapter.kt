package com.wojdor.sharemoments.application.gallery

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.wojdor.sharemoments.R
import com.wojdor.sharemoments.application.util.inflate
import com.wojdor.sharemoments.domain.Miniature
import kotlinx.android.synthetic.main.item_miniature.view.*
import kotlin.properties.Delegates

class GalleryAdapter(private val clickCallback: (Miniature) -> Unit)
    : RecyclerView.Adapter<GalleryAdapter.MiniatureViewHolder>() {

    var miniatures by Delegates.observable(emptyList<Miniature>()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MiniatureViewHolder(parent.inflate(R.layout.item_miniature))

    override fun getItemCount() = miniatures.size

    override fun onBindViewHolder(holder: MiniatureViewHolder, position: Int) = holder.bind(miniatures[position], clickCallback)

    class MiniatureViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(miniature: Miniature, clickCallback: (Miniature) -> Unit) = with(view) {
            Glide.with(context)
                    .load(miniature.photoMiniatureUrl)
                    .into(view.itemMiniaturePhotoIv)
            setOnClickListener { clickCallback(miniature) }
        }
    }
}
