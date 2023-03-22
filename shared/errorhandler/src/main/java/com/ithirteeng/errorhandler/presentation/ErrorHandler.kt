package com.ithirteeng.errorhandler.presentation

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.ithirteeng.errorhandler.ui.ErrorDialogFragment

object ErrorHandler {

    private val dialogFragment = ErrorDialogFragment()

    fun showErrorDialog(
        fragmentManager: FragmentManager,
        error: String,
        description: String
    ) {
        dialogFragment.setupDialogTextViews(error, description)
        setupDialogFragment()
        dialogFragment.show(fragmentManager, "review_dialog")
    }

    private fun setupDialogFragment() {
        dialogFragment.dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogFragment.dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogFragment.setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme)
    }
}