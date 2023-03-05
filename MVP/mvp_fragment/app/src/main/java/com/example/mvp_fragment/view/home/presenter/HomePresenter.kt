package com.example.mvp_fragment.view.home.presenter

import androidx.fragment.app.Fragment
import com.example.mvp_fragment.view.calendar.CalendarFragment
import com.example.mvp_fragment.view.home.adapter.CalendarPagerAdapterContract
import java.time.LocalDate


class HomePresenter : HomeContract.Presenter {
    override lateinit var view: HomeContract.View
    override lateinit var calendarPagerView: CalendarPagerAdapterContract.View
    override var calendarPagerModel: CalendarPagerAdapterContract.Model? = null
        set(value) {
            field = value
            field?.onPagerCreateFragment = { onPagerCreateFragmentListener(it) }
        }

    private var mDate: LocalDate = LocalDate.now()
    private var mPrevPos: Int = 0

    private fun onPagerCreateFragmentListener(position: Int): Fragment {
        return CalendarFragment(LocalDate.now().plusMonths(position.toLong()))
    }

    fun changeDisplayDate(position: Int) {
        mDate = mDate.plusMonths(comparePos(position).toLong())
        mPrevPos = position
        view.changeDisplayDate(mDate.year.toString(), mDate.monthValue.toString())
    }

    private fun comparePos(curPos: Int): Int {
        return if (curPos < mPrevPos) {
            -1
        } else if (curPos > mPrevPos) {
            1
        } else {
            0
        }
    }
}