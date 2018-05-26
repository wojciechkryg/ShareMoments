package com.wojdor.sharemoments.application.editphoto

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.wojdor.sharemoments.R
import com.wojdor.sharemoments.application.model.Filter
import com.wojdor.sharemoments.application.util.inflate
import kotlinx.android.synthetic.main.item_filter.view.*

class FiltersAdapter(private val filters: List<Filter>, private val clickCallback: (Filter) -> Unit)
    : RecyclerView.Adapter<FiltersAdapter.FilterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FilterViewHolder(parent.inflate(R.layout.item_filter))

    override fun getItemCount() = filters.size

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) = holder.bind(filters[position], clickCallback)

    class FilterViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(filter: Filter, clickCallback: (Filter) -> Unit) = with(view) {
            filterApplyFilterFab.setImageResource(filter.resIcon)
            setOnClickListener { clickCallback(filter) }
        }
    }
}