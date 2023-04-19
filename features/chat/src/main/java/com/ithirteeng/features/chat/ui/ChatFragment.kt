package com.ithirteeng.features.chat.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.features.chat.R
import com.ithirteeng.features.chat.databinding.FragmentChatBinding

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

        return binding.root
    }

    private fun onBackButtonClick() {
        binding.backButton.setOnClickListener {

        }
    }

    private fun setupChatArguments() {
        chatId = arguments?.getString(CHAT_ID_KEY).toString()
        chatName = arguments?.getString(CHAT_NAME_KEY).toString()
    }

    private fun setupViews() {
        binding.fragmentNameTextView.text = chatName
    }

}