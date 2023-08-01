package com.example.fakestoreandro.ui.customview

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.fakestoreandro.R
import com.example.fakestoreandro.databinding.LayoutProwBinding

class PRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = LayoutProwBinding.inflate(
        LayoutInflater.from(context), this
    )

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PRow)

        typedArray.apply {

            val icon = getDrawable(R.styleable.PRow_startIcon)
            val text = getString(R.styleable.PRow_text)

            setRowStartIcon(icon = icon)
            setRowText(text)
        }.recycle()
    }

    fun setRowStartIcon(icon: Drawable?) {
        icon?.let {
            binding.ivIcon.setImageDrawable(icon)
        }
    }

    fun setRowText(text: String?) {
        binding.tvText.text = text
    }

    fun setOnRowClickListener(listener: () -> Unit) {
        binding.layoutContainer.setOnClickListener { listener.invoke() }
    }
}