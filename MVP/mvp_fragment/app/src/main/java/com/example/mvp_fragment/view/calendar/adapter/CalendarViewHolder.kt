package com.example.mvp_fragment.view.calendar.adapter

import android.content.Context
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mvp_fragment.R
import com.example.mvp_fragment.data.NoteItem
import com.example.mvp_fragment.databinding.ItemCalendarDateBinding
import java.time.LocalDate

class CalendarViewHolder(
    private val binding: ItemCalendarDateBinding,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(
        width: Int,
        height: Int,
        calendarDate: LocalDate,
        currentDate: LocalDate,
        noteList: List<NoteItem>?
    ) {
        binding.textviewCalendarDate.text = (currentDate.dayOfMonth).toString()
        if (currentDate.monthValue != calendarDate.monthValue) {
            binding.textviewCalendarDate.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.light_gray
                )
            )
        }
        noteList?.forEach {
            binding.linearlayoutCalendarDateNote.addView(
                ImageView(context).apply {
                    setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.calendar_note_12dp
                        )
                    )
                    if (it.isFavorite) {
                        setColorFilter(
                            ContextCompat.getColor(
                                context,
                                R.color.calendar_note_activate
                            )
                        )
                    } else {
                        setColorFilter(
                            ContextCompat.getColor(
                                context,
                                R.color.calendar_note_inactivated
                            )
                        )
                    }
                }
            )
        }
        binding.root.layoutParams = ConstraintLayout.LayoutParams(width, height)
        binding.root.requestLayout()
    }

}
