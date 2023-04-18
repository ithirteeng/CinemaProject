package com.ithirteeng.features.discussions.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ithirteeng.features.discussions.R
import com.ithirteeng.features.discussions.databinding.ChatItemLayoutBinding
import com.ithirteeng.features.discussions.domain.entity.ChatEntity

class ChatsAdapter(
    private val onChatClick: (chatEntity: ChatEntity) -> Unit,
) : ListAdapter<ChatEntity, ChatsAdapter.ChatViewHolder>(ChatDiffCallBack) {

    inner class ChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ChatItemLayoutBinding.bind(view)

        private lateinit var chatEntity: ChatEntity

        init {
            binding.clickableView.setOnClickListener {
                onChatClick(chatEntity)
            }
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(chatEntity: ChatEntity) {
            this.chatEntity = chatEntity
            binding.chatNameTextView.text = chatEntity.chatName
            binding.lastMessageTextView.text = chatEntity.lastMessage.text
            Glide
                .with(binding.root)
                .load(chatEntity.lastMessage.authorAvatar)
                .placeholder(binding.root.context.getDrawable(com.ithirteeng.component.design.R.drawable.image_placeholder))
                .error(binding.root.context.getDrawable(com.ithirteeng.component.design.R.drawable.image_placeholder))
                .into(binding.chatImageView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsAdapter.ChatViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.chat_item_layout, parent, false)
        return ChatViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ChatsAdapter.ChatViewHolder, position: Int) {
        val entity = getItem(position)
        holder.bind(entity)
    }


}

object ChatDiffCallBack : DiffUtil.ItemCallback<ChatEntity>() {
    override fun areItemsTheSame(oldItem: ChatEntity, newItem: ChatEntity): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ChatEntity, newItem: ChatEntity): Boolean {
        return oldItem.chatId == newItem.chatId
    }

}