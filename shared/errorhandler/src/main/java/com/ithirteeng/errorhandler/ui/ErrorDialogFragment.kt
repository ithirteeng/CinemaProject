package com.ithirteeng.errorhandler.ui

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import com.ithirteeng.component.design.R.style
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.errorhandler.presentation.ErrorDialogFragmentViewModel
import com.ithirteeng.shared.errorhandler.R
import com.ithirteeng.shared.errorhandler.databinding.ErrorDialogLayoutBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.HttpException

class ErrorDialogFragment : DialogFragment() {

    private lateinit var binding: ErrorDialogLayoutBinding

    private val viewModel: ErrorDialogFragmentViewModel by viewModel()

    private lateinit var errorModel: ErrorModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = requireActivity().layoutInflater.inflate(R.layout.error_dialog_layout, null)
        binding = ErrorDialogLayoutBinding.bind(view)

        onCloseButtonCLick(errorModel)
        hideKeyboard()

        binding.errorTextView.text = errorModel.errorCode.toString()
        binding.errorDescriptionTextView.text = errorModel.errorDescription.toString()

        val builder =
            AlertDialog.Builder(requireActivity(), style.Theme_CinemaProject_ErrorDialogTheme)
        return builder.setView(view).create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, style.Theme_CinemaProject_ErrorDialogTheme)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        if (errorModel.error is HttpException && errorModel.errorCode == 401) {
            viewModel.navigateToLoginFragment()
            viewModel.deleteToken()
        }
    }

    private fun onCloseButtonCLick(errorModel: ErrorModel) {
        binding.exitButton.setOnClickListener {
            if (errorModel.error is HttpException && errorModel.errorCode == 401) {
                this.dismiss()
                viewModel.navigateToLoginFragment()
                viewModel.deleteToken()
            } else {
                this.dismiss()
            }
        }
    }

    fun setupDialogTextViews(errorModel: ErrorModel?) {
        this.errorModel = errorModel ?: ErrorModel(-1)
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager =
            activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = activity?.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}