package com.example.mvp_fragment.view.addnote.contract

import androidx.fragment.app.Fragment

interface AddNoteContract {
    interface View {
        fun popBackFragment()

    }

    interface Presenter {
        var view: View

        var onNavClickFunc: ((android.view.View) -> Unit)?

    }
}