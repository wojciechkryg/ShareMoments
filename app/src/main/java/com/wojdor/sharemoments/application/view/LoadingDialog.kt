package com.wojdor.sharemoments.application.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.wojdor.sharemoments.R

class LoadingDialog(context: Context) : Dialog(context) {

    init {
        window.setBackgroundDrawableResource(android.R.color.transparent)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)
    }
}
