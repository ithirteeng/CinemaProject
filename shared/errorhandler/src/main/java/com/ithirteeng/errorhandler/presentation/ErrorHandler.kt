package com.ithirteeng.errorhandler.presentation

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.ithirteeng.component.design.R
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.errorhandler.ui.ErrorDialogFragment
import com.ithirteeng.shared.network.common.NoConnectivityException
import retrofit2.HttpException

object ErrorHandler {

    private val dialogFragment = ErrorDialogFragment()

    fun showErrorDialog(
        context: Context,
        fragmentManager: FragmentManager,
        errorModel: ErrorModel,
    ) {
        Log.e("ERROR_TAG", "exception: ", errorModel.error)
        if (errorModel.error is HttpException ||
            errorModel.error is NoConnectivityException ||
            errorModel.error == null
        ) {

            if (dialogFragment.isAdded) {
                return
            } else {
                dialogFragment.setupDialogTextViews(setupError(context, errorModel))
                setupDialogFragment()

                dialogFragment.show(fragmentManager, "review_dialog")
            }


        }
    }

    private fun setupDialogFragment() {
        dialogFragment.dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogFragment.dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogFragment.setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme)
    }

    private fun setupError(context: Context, errorModel: ErrorModel): ErrorModel {
        val error = errorModel.error
        return when (val errorCode = errorModel.errorCode) {
            500 -> ErrorModel(errorCode, context.getString(R.string.internal_server_error), error)
            401 -> ErrorModel(errorCode, context.getString(R.string.unauthorized_error), error)
            403 -> ErrorModel(errorCode, context.getString(R.string.forbidden), error)
            404 -> ErrorModel(errorCode, context.getString(R.string.not_found), error)
            409 -> {
                if (errorModel.errorDescription == context.getString(R.string.movie_collection_error)) {
                    ErrorModel(errorCode, context.getString(R.string.movie_collection_error), error)
                } else {
                    ErrorModel(errorCode, context.getString(R.string.conflict_error), error)
                }

            }
            422 -> ErrorModel(errorCode, errorModel.errorDescription, error)
            1309 -> ErrorModel(errorCode, context.getString(R.string.connection_error), error)
            else -> ErrorModel(errorCode, context.getString(R.string.developer_goofy), error)
        }
    }
}