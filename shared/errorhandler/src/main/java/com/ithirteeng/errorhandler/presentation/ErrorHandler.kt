package com.ithirteeng.errorhandler.presentation

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.ithirteeng.component.design.R.string
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.errorhandler.ui.ErrorDialogFragment

object ErrorHandler {

    private val dialogFragment = ErrorDialogFragment()

    fun showErrorDialog(
        fragmentManager: FragmentManager,
        error: Int,
        description: String?
    ) {
        dialogFragment.setupDialogTextViews(setupError(error, description))
        setupDialogFragment()
        dialogFragment.show(fragmentManager, "review_dialog")
    }

    private fun setupDialogFragment() {
        dialogFragment.dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogFragment.dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogFragment.setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme)
    }

    private fun setupError(errorCode: Int, errorDescription: String?): ErrorModel {
        return when (errorCode) {
            500 -> ErrorModel(errorCode, null, string.internal_server_error)
            401 -> ErrorModel(errorCode, null, string.unauthorized_error)
            403 -> ErrorModel(errorCode, null, string.forbidden)
            404 -> ErrorModel(errorCode, null, string.not_found)
            422 -> ErrorModel(errorCode, errorDescription, null)
            0 -> ErrorModel(errorCode, null, string.developer_goofy)
            else -> ErrorModel(errorCode, null, string.connection_error)
        }
    }
}