package com.example.mvp_fragment.view.calendar.adapter

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mvp_fragment.R
import com.example.mvp_fragment.databinding.ItemCalendarDateBinding
import java.time.LocalDate

class CalendarViewHolder(private val binding: ItemCalendarDateBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(width: Int, height: Int, itemDate: LocalDate, curDate: LocalDate) {
        binding.textviewCalendarDate.text = (itemDate.dayOfMonth).toString()
        if(itemDate.monthValue != curDate.monthValue) {
            binding.textviewCalendarDate.setTextColor(ContextCompat.getColor(context, R.color.light_gray))
        }
        binding.root.layoutParams = ConstraintLayout.LayoutParams(width, height)
        binding.root.requestLayout()
    }

}
