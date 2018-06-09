package com.wojdor.sharemoments.application.util

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(layoutRes: Int): View = LayoutInflater.from(context).inflate(layoutRes, this, false)

fun RecyclerView.addPagination(layoutManager: LinearLayoutManager,
                               paginationCondition: () -> Boolean, onMore: () -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            with(layoutManager) {
                if (paginationCondition()
                        && findLastVisibleItemPosition() == itemCount - 1
                        && findFirstVisibleItemPosition() > 0
                        && (dx != 0 || dy != 0)) {
                    onMore()
                }
            }
        }
    })
}
