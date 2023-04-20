package com.ithirteeng.features.chat.ui

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.errorhandler.presentation.ErrorHandler
import com.ithirteeng.features.chat.R
import com.ithirteeng.features.chat.databinding.FragmentChatBinding
import com.ithirteeng.features.chat.presentation.ChatFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatFragment : Fragment() {

    companion object {

        private const val CHAT_ID_KEY = "CHAT_ID_KEY"
        private const val CHAT_NAME_KEY = "CHAT_NAME_KEY"

        fun provideChatFragment(chatId: String, chatName: String) = FragmentScreen {
            ChatFragment().apply {
                val bundle = Bundle()
                bundle.putString(CHAT_ID_KEY, chatId)
                bundle.putString(CHAT_NAME_KEY, chatName)
                arguments = bundle
            }
        }
    }

    private lateinit var binding: FragmentChatBinding

    private val viewModel: ChatFragmentViewModel by viewModel()

    private lateinit var chatId: String

    private lateinit var chatName: String

    private val chatAdapter = ChatAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val layout = inflater.inflate(R.layout.fragment_chat, container, false)
        binding = FragmentChatBinding.bind(layout)

        setupChatArguments()
        setupViews()
        onBackButtonClick()
        onSendButtonClick()

        binding.chatsRecyclerView.adapter = chatAdapter
        viewModel.initSocket(chatId)

        onGettingUserId()
        return binding.root
    }

    override fun onPause() {
        viewModel.closeSocket()
        super.onPause()
    }

    private fun onBackButtonClick() {
        binding.backButton.setOnClickListener {
            viewModel.exit()
        }
    }

    private fun setupChatArguments() {
        chatId = arguments?.getString(CHAT_ID_KEY).toString()
        chatName = arguments?.getString(CHAT_NAME_KEY).toString()
    }

    private fun setupViews() {
        binding.fragmentNameTextView.text = chatName
    }

    private fun onGettingUserId() {
        viewModel.makeGetUserIdRequest { handleErrors(it) }
        binding.progressBar.visibility = View.VISIBLE
        binding.sendButton.isEnabled = false
        binding.messageEditText.isEnabled = false
        viewModel.getUserIdLiveData().observe(this.viewLifecycleOwner) {
            onGettingMessagesList(it)
        }
    }

    private fun onGettingMessagesList(userId: String) {
        viewModel.getMessagesList(userId)
        viewModel.getMessagesListLiveData().observe(this.viewLifecycleOwner) {
            binding.sendButton.isEnabled = true
            binding.messageEditText.isEnabled = true
            binding.progressBar.visibility = View.GONE
            chatAdapter.submitList(it)
            binding.chatsRecyclerView.scrollToPosition(chatAdapter.itemCount - 1)
        }
    }

    private fun onSendButtonClick() {
        binding.sendButton.setOnClickListener {
            val string = binding.messageEditText.text.toString().trim()
            if (string.isNotEmpty()) {
                viewModel.sendMessage(string)
                binding.progressBar.visibility = View.VISIBLE
                binding.sendButton.isEnabled = false
                binding.messageEditText.isEnabled = false
            }
            hideKeyboard()
            binding.messageEditText.setText("")
        }
    }

    private fun handleErrors(errorModel: ErrorModel) {
        childFragmentManager.executePendingTransactions()
        ErrorHandler.showErrorDialog(requireContext(), childFragmentManager, errorModel)
        binding.progressBar.visibility = View.GONE
        binding.sendButton.isEnabled = true
        binding.messageEditText.isEnabled = true
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