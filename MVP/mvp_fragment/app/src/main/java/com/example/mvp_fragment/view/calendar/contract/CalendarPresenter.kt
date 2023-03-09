package com.example.mvp_fragment.view.calendar.contract

import android.os.Bundle
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

class CalendarPresenter : CalendarContract.Presenter {
    override lateinit var view: CalendarContract.View
    override lateinit var calendarAdapterView: CalendarAdapterContract.View
    override lateinit var calendarAdapterModel: CalendarAdapterContract.Model
    override lateinit var noteRepository: NoteRepository
    private lateinit var date: LocalDate

    override fun updateCalendarData(arguments: Bundle?) {
        CoroutineScope(Dispatchers.Main).launch {
            date = if (arguments == null) {
                LocalDate.of(1, 1, 1)
            } else {
                LocalDate.of(
                    arguments.getInt("year", 1),
                    arguments.getInt("month", 1),
                    arguments.getInt("day", 1)
                )
            }
            calendarAdapterModel.updateDate(date)
            val data = arrayListOf<Pair<LocalDate, List<NoteItem>?>>()
            CalendarUtil.getDateList(date).forEach {
                val result = noteRepository.getNotes(DateFormatter.localDateToString(it))
                when (result) {
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