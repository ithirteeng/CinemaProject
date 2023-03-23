package com.ithirteeng.errorhandler.ui

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import com.ithirteeng.component.design.R.style
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.shared.errorhandler.R
import com.ithirteeng.shared.errorhandler.databinding.ErrorDialogLayoutBinding

class ErrorDialogFragment : DialogFragment() {

    private lateinit var binding: ErrorDialogLayoutBinding

    private var errorCode: String? = null
    private var errorDescription: String? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = requireActivity().layoutInflater.inflate(R.layout.error_dialog_layout, null)
        binding = ErrorDialogLayoutBinding.bind(view)
        onCloseButtonCLick()

        hideKeyboard()

        binding.errorTextView.text = errorCode
        binding.errorDescriptionTextView.text = errorDescription

        val builder = AlertDialog.Builder(requireActivity(), style.ErrorDialogTheme)
        return builder.setView(view).create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, style.ErrorDialogTheme)
    }

    private fun onCloseButtonCLick() {
        binding.exitButton.setOnClickListener {
            this.dismiss()
        }
    }

    fun setupDialogTextViews(errorModel: ErrorModel) {
        errorCode = errorModel.errorCode.toString()
        errorDescription = errorModel.errorDescription ?: ""
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