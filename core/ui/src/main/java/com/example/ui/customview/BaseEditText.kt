package com.example.ui.customview

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.example.util.extension.hideKeyboard

class BaseEditText constructor(
    context: Context,
    attrs: AttributeSet?
) : AppCompatEditText(context, attrs) {
    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)

        if(!focused) hideKeyboard()
    }
}