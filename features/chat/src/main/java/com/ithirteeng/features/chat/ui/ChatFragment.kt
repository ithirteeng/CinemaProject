package com.ithirteeng.features.chat.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
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

        viewModel.initSocket(chatId)

        onGettingMessagesList()
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

    private fun onGettingMessagesList() {
        viewModel.getMessagesList()
        viewModel.getMessagesListLiveData().observe(this.viewLifecycleOwner) {
            Log.d("CHAT_DEBUG", it.last().toString())
        }
    }

    private fun onSendButtonClick() {
        binding.sendButton.setOnClickListener {
            if (!binding.messageEditText.text.isNullOrEmpty()) {
                viewModel.sendMessage(binding.messageEditText.text.toString())
            }
        }
    }


}