package com.ithirteeng.errorhandler.presentation

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.ithirteeng.component.design.R
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.errorhandler.ui.ErrorDialogFragment

object ErrorHandler {

    private val dialogFragment = ErrorDialogFragment()

    fun showErrorDialog(
        context: Context,
        fragmentManager: FragmentManager,
        errorModel: ErrorModel
    ) {
        dialogFragment.setupDialogTextViews(setupError(context, errorModel))
        setupDialogFragment()
        dialogFragment.show(fragmentManager, "review_dialog")
    }

    private fun setupDialogFragment() {
        dialogFragment.dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogFragment.dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogFragment.setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme)
    }

    private fun setupError(context: Context, errorModel: ErrorModel): ErrorModel {
        return when (val errorCode = errorModel.errorCode) {
            500 -> ErrorModel(errorCode, context.getString(R.string.internal_server_error))
            401 -> ErrorModel(errorCode, context.getString(R.string.unauthorized_error))
            403 -> ErrorModel(errorCode, context.getString(R.string.forbidden))
            404 -> ErrorModel(errorCode, context.getString(R.string.not_found))
            422 -> ErrorModel(errorCode, errorModel.errorDescription)
            0 -> ErrorModel(errorCode, context.getString(R.string.developer_goofy))
            else -> ErrorModel(errorCode, context.getString(R.string.connection_error))
        }
    }
}