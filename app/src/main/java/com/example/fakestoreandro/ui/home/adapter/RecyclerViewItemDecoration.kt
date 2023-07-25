package com.example.fakestoreandro.ui.home.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.fakestoreandro.R

class RecyclerViewItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val margin8dp = parent.context.resources.getDimensionPixelSize(R.dimen.margin_8dp)

        outRect.apply {
            top = margin8dp
            bottom = margin8dp
            left = margin8dp
            right = margin8dp
        }

        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.left += margin8dp
        } else if (parent.getChildAdapterPosition(view) == parent.adapter?.itemCount?.minus(1)) {
            outRect.right += margin8dp
        }

    }
}