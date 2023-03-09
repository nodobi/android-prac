package com.example.mvp_fragment.view.home.contract

import android.os.Bundle
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
    override var onPageSelected: (Int) -> Unit = { onPageSelected(it) }
    private val initialPageCount = 1000
    private val initialPagePos = initialPageCount / 2

    private var mDate: LocalDate = LocalDate.now()
    private var mPrevPos: Int = initialPagePos

    override fun initPageCount() {
        calendarPagerModel?.setPageCount(initialPageCount)
        view.setPagePosition(initialPagePos)
    }

    private fun changeDisplayDate(position: Int) {
        mDate = mDate.plusMonths(comparePos(position).toLong())
        mPrevPos = position
        view.changeDisplayDate(mDate.year.toString(), mDate.monthValue.toString())
    }

    private fun onPageSelected(position: Int) {
        changeDisplayDate(position)
    }

    private fun onPagerCreateFragmentListener(position: Int): Fragment {
        val targetDate = LocalDate.now().plusMonths(position.toLong() - (initialPagePos))
        val calendarFragment = CalendarFragment().apply {
            arguments = Bundle().apply {
                putInt("year", targetDate.year)
                putInt("month", targetDate.monthValue)
                putInt("day", targetDate.dayOfMonth)
            }
        }
        return calendarFragment
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