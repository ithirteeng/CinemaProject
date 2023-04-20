package com.ithirteeng.features.chat.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ithirteeng.component.design.R.*
import com.ithirteeng.features.chat.R
import com.ithirteeng.features.chat.databinding.DateMessageItemBinding
import com.ithirteeng.features.chat.databinding.MineMessageItemBinding
import com.ithirteeng.features.chat.databinding.OthersMessageItemBinding
import com.ithirteeng.features.chat.domain.entity.MessageEntity
import com.ithirteeng.features.chat.domain.model.Message
import com.ithirteeng.features.chat.presentation.utils.DateHelper
import com.ithirteeng.features.chat.presentation.utils.MessagePadding
import com.ithirteeng.features.chat.presentation.utils.MessageType
import java.util.*


class ChatAdapter : ListAdapter<Message, RecyclerView.ViewHolder>(MessageDiffCallBack) {

    inner class OthersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = OthersMessageItemBinding.bind(view)

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(messageEntity: MessageEntity?) {
            binding.messageTextView.text = messageEntity?.text?.trim()
            binding.authorTextView.text = setupAuthorTextView(messageEntity)
            if (messageEntity != null) {
                if (!messageEntity.isFirst) {
                    binding.textsLayout.background = binding.root.resources.getDrawable(
                        R.drawable.others_square_message_background,
                        binding.root.context.theme
                    )
                } else {
                    binding.textsLayout.background = binding.root.resources.getDrawable(
                        R.drawable.others_message_background,
                        binding.root.context.theme
                    )
                }
            }
            if (messageEntity?.authorAvatar == "BAD") {
                binding.cardView.visibility = View.INVISIBLE
                setPaddingForView(binding.root, MessagePadding.SMALL, MessageType.OTHERS)
            } else {
                binding.cardView.visibility = View.VISIBLE
                setPaddingForView(binding.root, MessagePadding.MEDIUM, MessageType.OTHERS)
                binding.authorImageView.setupImageView(messageEntity?.authorAvatar, binding.root)
            }
        }
    }

    inner class MineViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = MineMessageItemBinding.bind(view)

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(messageEntity: MessageEntity?) {
            binding.messageTextView.text = messageEntity?.text?.trim()
            binding.authorTextView.text = setupAuthorTextView(messageEntity)
            if (messageEntity != null) {
                if (!messageEntity.isFirst) {
                    binding.textsLayout.background = binding.root.resources.getDrawable(
                        R.drawable.mine_square_message_background,
                        binding.root.context.theme
                    )
                } else {
                    binding.textsLayout.background = binding.root.resources.getDrawable(
                        R.drawable.mine_message_background,
                        binding.root.context.theme
                    )
                }
            }
            if (messageEntity?.authorAvatar == "BAD") {
                binding.cardView.visibility = View.INVISIBLE
                setPaddingForView(binding.root, MessagePadding.SMALL, MessageType.MINE)
            } else {
                binding.cardView.visibility = View.VISIBLE
                setPaddingForView(binding.root, MessagePadding.MEDIUM, MessageType.MINE)
                binding.authorImageView.setupImageView(messageEntity?.authorAvatar, binding.root)
            }
        }
    }

    inner class DateViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = DateMessageItemBinding.bind(view)

        fun bind(messageEntity: MessageEntity?) {
            val date = messageEntity?.creationDateTime.toString()

            if (DateHelper.checkIdDateIsToday(date)) {
                binding.textView.text = binding.root.resources.getString(string.today)
            } else {
                binding.textView.text = DateHelper.getDate(date)
            }
            setPaddingForView(binding.root, MessagePadding.BIG, MessageType.DATE)
        }
    }

    private fun setPaddingForView(
        view: View,
        paddingType: MessagePadding,
        messageType: MessageType,
    ) {
        val paddingVertical = when (paddingType) {
            MessagePadding.BIG -> view.context.resources.getDimension(dimen.padding_24)
            MessagePadding.MEDIUM -> view.context.resources.getDimension(dimen.padding_16)
            else -> view.context.resources.getDimension(dimen.padding_4)
        }
        if (messageType == MessageType.DATE) {
            val padding8 = view.context.resources.getDimension(dimen.padding_8)
            view.setPadding(0, padding8.toInt(), 0, paddingVertical.toInt())
        } else {
            view.setPadding(0, 0, 0, paddingVertical.toInt())
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun ImageView.setupImageView(url: String?, root: FrameLayout) {
        Glide
            .with(root)
            .load(url)
            .placeholder(root.context.getDrawable(com.ithirteeng.component.design.R.drawable.image_placeholder))
            .error(root.context.getDrawable(com.ithirteeng.component.design.R.drawable.image_placeholder))
            .into(this)

    }

    private fun setupAuthorTextView(messageEntity: MessageEntity?): String {
        return "${messageEntity?.authorName} • ${DateHelper.getTime(messageEntity?.creationDateTime.toString())}"
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> {
                val layout = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.mine_message_item, parent, false)
                MineViewHolder(layout)
            }
            1 -> {
                val layout = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.others_message_item, parent, false)
                OthersViewHolder(layout)
            }
            else -> {
                val layout = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.date_message_item, parent, false)
                DateViewHolder(layout)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = getItem(position)
        when (holder.itemViewType) {
            0 -> (holder as MineViewHolder).bind(message.messageEntity)
            1 -> (holder as OthersViewHolder).bind(message.messageEntity)
            else -> (holder as DateViewHolder).bind(message.messageEntity)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Message.MineMessage -> 0
            is Message.OthersMessage -> 1
            else -> 2
        }
    }

    override fun submitList(list: List<Message>?) {
        super.submitList(list?.let { ArrayList(it) })
    }
}

object MessageDiffCallBack : DiffUtil.ItemCallback<Message>() {
    override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem.messageEntity?.messageId == newItem.messageEntity?.messageId &&
                oldItem.messageEntity?.authorAvatar == newItem.messageEntity?.authorAvatar
    }

    override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem.messageEntity == newItem.messageEntity
    }

}