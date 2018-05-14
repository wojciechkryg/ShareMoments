package com.wojdor.sharemoments.application.base

interface BaseContract {

    interface BaseView<out T> {

        val presenter: T
    }

    interface BasePresenter<out T> {

        val view: T

        fun onAttach()

        fun onDetach()
    }
}
