package com.ithirteeng.errorhandler.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.ithirteeng.component.design.R.*
import com.ithirteeng.shared.errorhandler.R
import com.ithirteeng.shared.errorhandler.databinding.ErrorDialogLayoutBinding

class ErrorDialogFragment : DialogFragment() {

    private lateinit var binding: ErrorDialogLayoutBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = requireActivity().layoutInflater.inflate(R.layout.error_dialog_layout, null)
        binding = ErrorDialogLayoutBinding.bind(view)

        val builder = AlertDialog.Builder(requireActivity(), style.ErrorDialogTheme)
        return builder.setView(view).create()
    }

    fun setupDialogTextViews(error: String, description: String) {
        binding.errorTextView.text = error
        binding.errorDescriptionTextView.text = description
    }
}