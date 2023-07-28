package com.example.fakestoreandro.ui.customview.snackbar

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.example.fakestoreandro.R

enum class SnackbomType(
    @DrawableRes val background: Int,
    @ColorRes val textColor: Int,
) {
    SUCCESS(background = R.drawable.bg_snackbom_success, textColor = R.color.white),
    ERROR(background = R.drawable.bg_snackbom_error, textColor = R.color.white),
    INFO(background = R.drawable.bg_snackbom_info, textColor = R.color.white)
}