package com.example.mvp_fragment.view.main.presenter

import android.view.MenuItem
import com.example.mvp_fragment.R
import com.example.mvp_fragment.view.favorite.FavoriteFragment
import com.example.mvp_fragment.view.home.HomeFragment
import com.example.mvp_fragment.view.note.NoteFragment

class MainPresenter : MainContract.Presenter {
    override lateinit var view: MainContract.View
    override var onNavItemSelectFunc: ((MenuItem) -> Boolean) = { onNavItemSelectListener(it) }

    private fun onNavItemSelectListener(selected: MenuItem): Boolean {
        when (selected.itemId) {
            R.id.item_note -> {
                view.changeFragment(NoteFragment())
            }
            R.id.item_home -> {
                view.changeFragment(HomeFragment())
            }
            R.id.item_favorite -> {
                view.changeFragment(FavoriteFragment())
            }
        }
        return true
    }
}