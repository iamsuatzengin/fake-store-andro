package com.example.fakestoreandro.ui.customview.snackbar

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.example.fakestoreandro.R

enum class SnackbomType(
    @DrawableRes val background: Int,
    val icon: Int,
    @ColorRes val textColor: Int,
) {
    SUCCESS(
        background = R.drawable.bg_snackbom_success,
        icon = R.drawable.ic_circle_done,
        textColor = R.color.white
    ),
    ERROR(
        background = R.drawable.bg_snackbom_error,
        R.drawable.ic_circle_xmark,
        textColor = R.color.white
    ),
    INFO(
        background = R.drawable.bg_snackbom_info,
        icon = R.drawable.ic_circle_info,
        textColor = R.color.white
    )
}