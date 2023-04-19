package com.ithirteeng.features.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ithirteeng.features.chat.databinding.FragmentChatBinding

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val layout = inflater.inflate(R.layout.fragment_chat, container, false)
        binding = FragmentChatBinding.bind(layout)

        return binding.root
    }
}