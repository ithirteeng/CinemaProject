package com.ithirteeng.features.chat.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
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
import java.util.*


class ChatAdapter : ListAdapter<Message, RecyclerView.ViewHolder>(MessageDiffCallBack) {

    inner class OthersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = OthersMessageItemBinding.bind(view)

        fun bind(messageEntity: MessageEntity?) {
            binding.messageTextView.text = messageEntity?.text
            binding.authorTextView.text = setupAuthorTextView(messageEntity)
            if (messageEntity?.authorAvatar == "BAD") {
                binding.cardView.visibility = View.INVISIBLE
                setPaddingForView(binding.root, MessagePadding.SMALL, false)
            } else {
                binding.cardView.visibility = View.VISIBLE
                setPaddingForView(binding.root, MessagePadding.MEDIUM, false)
                binding.authorImageView.setupImageView(messageEntity?.authorAvatar, binding.root)
            }
        }
    }

    inner class MineViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = MineMessageItemBinding.bind(view)

        fun bind(messageEntity: MessageEntity?) {
            binding.messageTextView.text = messageEntity?.text
            binding.authorTextView.text = setupAuthorTextView(messageEntity)
            if (messageEntity?.authorAvatar == "BAD") {
                binding.cardView.visibility = View.INVISIBLE
                setPaddingForView(binding.root, MessagePadding.SMALL, false)
            } else {
                binding.cardView.visibility = View.VISIBLE
                setPaddingForView(binding.root, MessagePadding.MEDIUM, false)
                binding.authorImageView.setupImageView(messageEntity?.authorAvatar, binding.root)
            }
        }
    }

    inner class DateViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = DateMessageItemBinding.bind(view)

        fun bind(messageEntity: MessageEntity?) {
            binding.textView.text = DateHelper.getDate(messageEntity?.creationDateTime.toString())
            setPaddingForView(binding.root, MessagePadding.BIG, true)
        }
    }

    private fun setPaddingForView(view: View, paddingType: MessagePadding, isDate: Boolean) {
        val padding = when (paddingType) {
            MessagePadding.BIG -> view.context.resources.getDimension(dimen.padding_24)
            MessagePadding.MEDIUM -> view.context.resources.getDimension(dimen.padding_16)
            else -> view.context.resources.getDimension(dimen.padding_4)
        }
        if (isDate) {
            val padding8 = view.context.resources.getDimension(dimen.padding_8)
            view.setPadding(0, padding8.toInt(), 0, padding.toInt())
        } else {
            view.setPadding(0, 0, 0, padding.toInt())
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun ImageView.setupImageView(url: String?, root: ConstraintLayout) {
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