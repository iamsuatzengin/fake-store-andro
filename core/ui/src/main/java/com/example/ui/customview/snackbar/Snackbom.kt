package com.example.ui.customview.snackbar

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.example.ui.R
import com.google.android.material.snackbar.BaseTransientBottomBar

class Snackbom(
    parent: ViewGroup,
    snackbomView: SnackbomView
) : BaseTransientBottomBar<Snackbom>(parent, snackbomView, snackbomView) {

    private val marginTop = snackbomView.resources.getDimensionPixelSize(R.dimen.margin_24dp)

    init {
        getView().apply {
            setBackgroundColor(ContextCompat.getColor(view.context, android.R.color.transparent))
            setPadding(0, marginTop, 0, 0)

            val params = layoutParams as FrameLayout.LayoutParams

            params.gravity = Gravity.TOP

            layoutParams = params
        }
    }

    companion object {

        fun make(view: View, text: String, type: SnackbomType): Snackbom {

            val parent = view.findSuitableParent() ?: throw IllegalArgumentException(
                "No suitable parent found from the given view. Please provide a valid view."
            )

            val snackbomView = LayoutInflater.from(view.context).inflate(
                R.layout.layout_snackbom,
                parent,
                false
            ) as SnackbomView

            snackbomView.apply {
                setText(text)
                setType(type)
            }

            return Snackbom(parent, snackbomView)
        }
    }
}

