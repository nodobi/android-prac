package com.example.mvp_fragment.view.addnote.contract

import android.view.View

class AddNotePresenter : AddNoteContract.Presenter {
    override lateinit var view: AddNoteContract.View
    override var onNavClickFunc: ((View) -> Unit)? = { onNavClickFunc(it) }

    private fun onNavClickFunc(view: View) {
        this.view.popBackFragment()
    }
}