package com.example.food.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class Decoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val index = parent.getChildAdapterPosition(view) + 1

        if (index % 3 == 0) {
            outRect.set(10, 10, 10, 30)
        } else {
            outRect.set(10, 10, 10, 0)
        }
    }

}