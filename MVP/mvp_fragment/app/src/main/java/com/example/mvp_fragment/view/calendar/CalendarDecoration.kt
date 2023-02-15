package com.example.mvp_fragment.view.calendar

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mvp_fragment.R


class CalendarDecoration(private val context: Context) : RecyclerView.ItemDecoration() {

    private val mDivider : Drawable = ContextCompat.getDrawable(context, R.drawable.home_calendar_divider)!!

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0 until childCount - 7) {
            val child: View = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top: Int = child.bottom + params.bottomMargin
            val bottom = top + mDivider.intrinsicHeight
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)
        }

        if(parent.childCount != 0) {
            val child: View = parent.getChildAt(0)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top: Int = child.top - params.bottomMargin
            val bottom: Int = top + mDivider.intrinsicHeight
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)
        }

        super.onDrawOver(c, parent, state)
    }
}