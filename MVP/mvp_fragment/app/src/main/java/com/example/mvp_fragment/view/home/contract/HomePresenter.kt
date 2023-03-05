package com.example.mvp_fragment.view.home.contract

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

    private val initialPageCount = 1000
    private val initialPagePos = initialPageCount / 2

    private var mDate: LocalDate = LocalDate.now()
    private var mPrevPos: Int = initialPagePos

    fun changeDisplayDate(position: Int) {
        mDate = mDate.plusMonths(comparePos(position).toLong())
        mPrevPos = position
        view.changeDisplayDate(mDate.year.toString(), mDate.monthValue.toString())
    }

    override fun initPageCount() {
        calendarPagerModel?.setPageCount(initialPageCount)
        view.setPagePosition(initialPagePos)
    }

    private fun onPagerCreateFragmentListener(position: Int): Fragment {
        return CalendarFragment(LocalDate.now().plusMonths(position.toLong() - (initialPagePos)))
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