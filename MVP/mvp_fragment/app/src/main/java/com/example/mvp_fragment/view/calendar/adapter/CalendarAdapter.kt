package com.example.mvp_fragment.view.calendar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvp_fragment.databinding.ItemCalendarDateBinding
import com.example.mvp_fragment.util.CalendarUtil
import java.time.LocalDate

class CalendarAdapter(private val context: Context) :
    RecyclerView.Adapter<CalendarViewHolder>(), CalendarAdapterContract.View,
    CalendarAdapterContract.Model {
    private var width: Int = 0
    private var height: Int = 0
    private lateinit var date: LocalDate
    private lateinit var data: List<LocalDate>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        if (width == 0 && height == 0) {
            width = parent.measuredWidth
            height = parent.measuredHeight
        }
        return CalendarViewHolder(
            ItemCalendarDateBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            ), context
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.onBind(
            width / 7,
            height / CalendarUtil.getCalendarWeekCnt(date),
            data[position],
            date
        )
    }

    override fun updateDateList(date: LocalDate) {
        this.date = date
        data = CalendarUtil.getDateList(date)
    }


}