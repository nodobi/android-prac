package com.example.mvp_fragment.view.calendar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvp_fragment.data.source.note.NoteRepository
import com.example.mvp_fragment.data.source.note.local.NoteDatabase
import com.example.mvp_fragment.data.source.note.local.NoteLocalDataSource
import com.example.mvp_fragment.databinding.FragmentCalendarBinding
import com.example.mvp_fragment.view.base.BaseFragment
import com.example.mvp_fragment.view.calendar.contract.CalendarContract
import com.example.mvp_fragment.view.calendar.contract.CalendarPresenter
import com.example.mvp_fragment.view.calendar.adapter.CalendarAdapter

class CalendarFragment : BaseFragment<FragmentCalendarBinding>(),
    CalendarContract.View {
    private lateinit var mPresenter: CalendarPresenter
    private lateinit var mAdapter: CalendarAdapter

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCalendarBinding {
        return FragmentCalendarBinding.inflate(inflater, container, false)
    }

    override fun initViews() {
        mAdapter = CalendarAdapter(requireContext())
        mPresenter = CalendarPresenter().apply {
            view = this@CalendarFragment
            calendarAdapterModel = mAdapter
            calendarAdapterView = mAdapter
            noteRepository = NoteRepository.apply {
                noteLocalDataSource = NoteLocalDataSource.apply {
                    noteDao = NoteDatabase.getInstance(requireContext()).noteDao()
                }
            }
        }
        binding.recyclerviewCalendar.apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(requireContext(), 7)
            addItemDecoration(CalendarDecoration(requireContext()))
            overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

        mPresenter.updateCalendarData(arguments)
    }
}