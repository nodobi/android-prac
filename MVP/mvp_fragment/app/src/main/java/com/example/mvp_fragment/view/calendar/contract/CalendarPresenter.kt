package com.example.mvp_fragment.view.calendar.contract

import com.example.mvp_fragment.data.NoteItem
import com.example.mvp_fragment.data.source.note.NoteRepository
import com.example.mvp_fragment.data.source.note.Result
import com.example.mvp_fragment.util.CalendarUtil
import com.example.mvp_fragment.util.DateFormatter
import com.example.mvp_fragment.view.calendar.adapter.CalendarAdapterContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class CalendarPresenter: CalendarContract.Presenter {
    override lateinit var view: CalendarContract.View
    override lateinit var calendarAdapterView: CalendarAdapterContract.View
    override lateinit var calendarAdapterModel: CalendarAdapterContract.Model
    override lateinit var noteRepository: NoteRepository

    override fun updateCalendarData(date: LocalDate) {
        CoroutineScope(Dispatchers.Main).launch {
            calendarAdapterModel.updateDate(date)
            val data = arrayListOf<Pair<LocalDate, List<NoteItem>?>>()
            CalendarUtil.getDateList(date).forEach {
                val result = noteRepository.getNotes(DateFormatter.localDateToString(it))
                when(result) {
                    is Result.Success<List<NoteItem>> -> {
                        data.add(Pair(it, result.data))
                    }
                    is Result.Error -> {
                        data.add(Pair(it, null))
                    }
                }
            }
            calendarAdapterModel.updateData(data.toList())
            calendarAdapterModel.notifyAdapter()
        }
    }
}