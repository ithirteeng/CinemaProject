package com.ithirteeng.features.profile.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.ithirteeng.features.profile.R.layout.pick_image_dialog_fragment_layout
import com.ithirteeng.features.profile.databinding.PickImageDialogFragmentLayoutBinding

class PickImageDialogFragment(
    private val onCameraClick: () -> Unit,
    private val onGalleryClick: () -> Unit,
) : DialogFragment() {

    private lateinit var binding: PickImageDialogFragmentLayoutBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = requireActivity().layoutInflater.inflate(pick_image_dialog_fragment_layout, null)

        binding = PickImageDialogFragmentLayoutBinding.bind(view)

        onCancelButtonClick()

        onCameraButtonClick()
        onGalleryButtonClick()

        val builder = AlertDialog.Builder(
            requireActivity(),
            com.ithirteeng.component.design.R.style.Theme_CinemaProject_DialogTheme
        )
        return builder.setView(view).create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            STYLE_NO_FRAME,
            com.ithirteeng.component.design.R.style.Theme_CinemaProject_DialogTheme
        )
    }

    private fun onCancelButtonClick() {
        binding.cancelButton.setOnClickListener {
            this.dismiss()
        }
    }

    private fun onCameraButtonClick() {
        binding.cameraButton.setOnClickListener {
            onCameraClick()
            this.dismiss()
        }
    }

    private fun onGalleryButtonClick() {
        binding.galleryButton.setOnClickListener {
            onGalleryClick()
            this.dismiss()
        }
    }
}