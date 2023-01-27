package com.example.mvp_fragment.view.main.presenter

import android.view.MenuItem
import androidx.fragment.app.Fragment

interface MainContract {
    interface View {
        fun changeFragment(fragment: Fragment)
    }

    interface Presenter {
        var view: MainContract.View
        var onNavItemSelectFunc: ((MenuItem) -> Boolean)
    }
}