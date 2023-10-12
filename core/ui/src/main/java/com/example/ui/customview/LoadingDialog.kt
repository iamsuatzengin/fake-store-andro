package com.example.ui.customview

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import com.example.ui.R

class LoadingDialog (context: Context): Dialog(context) {

    init {
        initDialog()
    }

    private fun initDialog() {
        val params = window?.attributes

        params?.gravity = Gravity.CENTER

        window?.attributes = params

        setTitle(null)
        setCancelable(false)
        setOnCancelListener(null)

        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.loading_layout)
    }

    fun showLoading(isLoading: Boolean) {
        if(isLoading) show()
        else dismiss()
    }
}