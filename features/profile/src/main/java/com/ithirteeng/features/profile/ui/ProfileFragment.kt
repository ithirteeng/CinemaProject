package com.ithirteeng.features.profile.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.errorhandler.presentation.ErrorHandler
import com.ithirteeng.features.profile.R
import com.ithirteeng.features.profile.databinding.FragmentProfileBinding
import com.ithirteeng.features.profile.presentation.ProfileFragmentViewModel
import com.ithirteeng.shared.userstorage.domain.entity.UserEntity
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : Fragment() {

    companion object {
        val provideProfileScreen = FragmentScreen { ProfileFragment() }
    }

    private lateinit var binding: FragmentProfileBinding

    private val viewModel: ProfileFragmentViewModel by viewModel()

    private val pickImageDialogFragment = PickImageDialogFragment(
        onCameraClick = { getCameraPicture() },
        onGalleryClick = { getGalleryPicture() }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val layout = inflater.inflate(R.layout.fragment_profile, container, false)
        binding = FragmentProfileBinding.bind(layout)

        viewModel.getProfileData { handleErrors(it) }
        onGettingProfileData()
        setupOnButtonClickFunctions()

        return binding.root
    }

    private fun onGettingProfileData() {
        viewModel.getProfileLiveData().observe(this.viewLifecycleOwner) {
            binding.nameTextView.text = it.fullName
            binding.emailTextView.text = it.email
            setAvatarImageByUrl(it.image)
            viewModel.saveUserdataLocally(UserEntity(it.fullName, it.email, it.image))
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setAvatarImageByUrl(url: String?) {
        Glide
            .with(binding.root)
            .load(url)
            .placeholder(requireContext().getDrawable(com.ithirteeng.component.design.R.drawable.image_placeholder))
            .error(requireContext().getDrawable(com.ithirteeng.component.design.R.drawable.image_placeholder))
            .into(binding.avatarImageView)
    }

    private fun setupOnButtonClickFunctions() {
        onCancelButtonClick()
        onChangeButtonClick()
    }

    private fun onChangeButtonClick() {
        binding.changeButton.setOnClickListener {
            showPickDialogFragment()
        }
    }

    private fun onCancelButtonClick() {
        binding.cancelButton.setOnClickListener {
            viewModel.logoutUser()
        }
    }

    private fun showPickDialogFragment() {
        setupDialogFragment()

        pickImageDialogFragment.show(childFragmentManager, "pick image dialog")
    }

    private var resultLauncher = registerForActivityResult(StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            doOnActivityResult(result)
        }
    }

    @Suppress("DEPRECATION")
    private fun doOnActivityResult(result: ActivityResult) {
        val bitmap = if (result.data?.data !is Uri) {
            result.data?.extras?.get("data") as Bitmap
        } else {
            val uri = result.data?.data
            BitmapFactory.decodeStream(
                uri?.let { requireActivity().contentResolver.openInputStream(it) },
                null,
                null
            )
        }

        bitmap?.let { viewModel.uploadUserAvatar(it) { error -> handleErrors(error) } }
    }

    private fun getCameraPicture() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLauncher.launch(takePictureIntent)
    }

    private fun getGalleryPicture() {
        val pickPhotoIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        resultLauncher.launch(pickPhotoIntent)
    }

    private fun setupDialogFragment() {
        pickImageDialogFragment.dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        pickImageDialogFragment.dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pickImageDialogFragment.setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme)
    }

    private fun handleErrors(errorModel: ErrorModel) {
        childFragmentManager.executePendingTransactions()
        ErrorHandler.showErrorDialog(requireContext(), childFragmentManager, errorModel)
    }
}