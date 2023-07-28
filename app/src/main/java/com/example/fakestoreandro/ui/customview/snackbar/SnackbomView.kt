package com.example.fakestoreandro.ui.customview.snackbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.fakestoreandro.databinding.ViewSnackbomBinding
import com.google.android.material.snackbar.ContentViewCallback

class SnackbomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), ContentViewCallback {

    private val binding: ViewSnackbomBinding = ViewSnackbomBinding.inflate(
        LayoutInflater.from(context), this
    )

    fun setText(text: String) {
        binding.tvSnackbarMessage.text = text
    }

    fun setType(type: SnackbomType) {
        setBackground(type.background)
        setMessageTextColor(type.textColor)
    }

    private fun setBackground(@DrawableRes background: Int) {
        binding.root.background = ContextCompat.getDrawable(this.context, background)
    }

    private fun setMessageTextColor(@ColorRes color: Int) {
        binding.tvSnackbarMessage.setTextColor(ContextCompat.getColor(this.context, color))
    }

    override fun animateContentIn(delay: Int, duration: Int) {}

    override fun animateContentOut(delay: Int, duration: Int) {}
}
