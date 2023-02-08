package com.example.mvp_fragment.view.base

interface BaseContract {
    interface View {

    }
    interface Presenter<T : View> {
        var view: T

    }
}