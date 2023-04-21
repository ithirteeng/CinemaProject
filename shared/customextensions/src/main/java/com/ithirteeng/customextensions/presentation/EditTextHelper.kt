package com.ithirteeng.customextensions.presentation

import android.text.InputFilter
import android.widget.EditText

fun EditText.setEditTextInputSpaceFilter() {
    val filter = InputFilter { source, _, _, _, _, _ ->
        if (source == " " || source.toString().contentEquals("\n")) {
            ""
        } else {
            null
        }
    }
    this.filters = arrayOf(filter)
}