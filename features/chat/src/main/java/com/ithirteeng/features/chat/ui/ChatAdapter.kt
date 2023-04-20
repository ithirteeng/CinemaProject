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
import com.ithirteeng.features.chat.R
import com.ithirteeng.features.chat.databinding.DateMessageItemBinding
import com.ithirteeng.features.chat.databinding.MineMessageItemBinding
import com.ithirteeng.features.chat.databinding.OthersMessageItemBinding
import com.ithirteeng.features.chat.domain.entity.MessageEntity
import com.ithirteeng.features.chat.domain.model.Message
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

class ChatAdapter : ListAdapter<Message, RecyclerView.ViewHolder>(MessageDiffCallBack) {

    inner class OthersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = OthersMessageItemBinding.bind(view)

        fun bind(messageEntity: MessageEntity?) {
            binding.messageTextView.text = messageEntity?.text
            binding.authorTextView.text = setupAuthorTextView(messageEntity)
            if (messageEntity?.authorAvatar == "BAD") {
                binding.cardView.visibility = View.INVISIBLE
            } else {
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
            } else {
                binding.authorImageView.setupImageView(messageEntity?.authorAvatar, binding.root)
            }
        }
    }

    inner class DateViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = DateMessageItemBinding.bind(view)

        fun bind(messageEntity: MessageEntity?) {
            binding.textView.text = setupDate(messageEntity)
        }

        private fun setupDate(messageEntity: MessageEntity?): String {
            val date = LocalDate.parse(
                messageEntity?.creationDateTime.toString().split("T")[0],
                DateTimeFormatter.ISO_LOCAL_DATE
            )

            val day = date.dayOfMonth
            val month = date.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
            return "$day $month"
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
        return "${messageEntity?.authorName} • ${getTime(messageEntity?.creationDateTime.toString())}"
    }

    private fun getTime(dateTime: String): String {
        val time = dateTime.split(".")[0]
        val localDateTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        val minutes =
            if (localDateTime.minute < 10) "0${localDateTime.minute}" else localDateTime.minute
        return "${localDateTime.hour}:${minutes}"
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