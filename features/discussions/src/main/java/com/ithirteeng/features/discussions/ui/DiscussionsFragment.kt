package com.ithirteeng.features.discussions.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.errorhandler.presentation.ErrorHandler
import com.ithirteeng.features.discussions.R
import com.ithirteeng.features.discussions.databinding.FragmentDiscussionsBinding
import com.ithirteeng.features.discussions.presentation.DiscussionsFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiscussionsFragment : Fragment() {

    companion object {
        val provideDiscussionsScreen = FragmentScreen { DiscussionsFragment() }
    }

    private lateinit var binding: FragmentDiscussionsBinding

    private val viewModel: DiscussionsFragmentViewModel by viewModel()

    private val chatsAdapter by lazy {
        ChatsAdapter {
            viewModel.navigateToChatScreen(it.chatId, it.chatName)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val layout = inflater.inflate(R.layout.fragment_discussions, container, false)
        binding = FragmentDiscussionsBinding.bind(layout)

        binding.chatsRecyclerView.adapter = chatsAdapter
        onGettingChatsList()
        onBackButtonClick()

        return binding.root
    }


    private fun onGettingChatsList() {
        viewModel.makeGetChatsRequest { handleErrors(it) }
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getChatsLiveData().observe(this.viewLifecycleOwner) {
            chatsAdapter.submitList(it)
            binding.progressBar.visibility = View.GONE
        }

    }

    private fun onBackButtonClick() {
        binding.backButton.setOnClickListener {
            viewModel.exit()
        }
    }

    private fun handleErrors(errorModel: ErrorModel) {
        childFragmentManager.executePendingTransactions()
        ErrorHandler.showErrorDialog(requireContext(), childFragmentManager, errorModel)
        binding.progressBar.visibility = View.GONE
    }
}