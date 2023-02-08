package com.example.mvp_fragment.view.main.presenter

import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.mvp_fragment.view.base.BaseContract

interface MainContract {
    interface View : BaseContract.View {
        fun changeFragment(fragment: Fragment)
    }

    interface Presenter : BaseContract.Presenter<View> {
        var onNavItemSelectFunc: ((MenuItem) -> Boolean)
    }
}