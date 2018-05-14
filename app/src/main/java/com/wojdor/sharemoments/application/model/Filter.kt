package com.wojdor.sharemoments.application.model

data class Filter(val name: String, val resIcon: Int, val callback: () -> Unit)
