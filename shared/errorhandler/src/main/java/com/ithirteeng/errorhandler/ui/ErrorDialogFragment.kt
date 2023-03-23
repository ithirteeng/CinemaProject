package com.ithirteeng.errorhandler.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.ithirteeng.component.design.R.style
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

    fun setupDialogTextViews(error: String, description: String) {
        errorCode = error
        errorDescription = description
    }
}