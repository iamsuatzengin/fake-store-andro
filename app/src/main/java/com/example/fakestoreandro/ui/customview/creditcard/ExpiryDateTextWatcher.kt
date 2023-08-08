package com.example.fakestoreandro.ui.customview.creditcard

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher

class ExpiryDateTextWatcher(
    private val setExpiryDate: (Editable) -> Unit
) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(editable: Editable?) {

        if (editable == null) return

        if (editable.isNotEmpty() && ((editable.length % 3) == 0)) {
            val c = editable.elementAt(editable.length - 1)
            if ('/' == c) {
                editable.delete(editable.length - 1, editable.length)
            }
        }
        if (editable.isNotEmpty() && (editable.length % 3) == 0) {
            val c = editable.elementAt(editable.length - 1)
            if (Character.isDigit(c) && TextUtils.split(
                    editable.toString(),
                    "/"
                ).size <= 2
            ) {
                editable.insert(editable.length - 1, "/")
            }
        }

        setExpiryDate(editable)
    }
}