package com.ithirteeng.features.collections.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ithirteeng.features.collections.R
import com.ithirteeng.features.collections.databinding.IconItemLayoutBinding

class ChooseIconAdapter(
    private val onIconClick: (imageId: Int) -> Unit,
) : ListAdapter<Int, ChooseIconAdapter.ChooseIconViewHolder>(IconDiffCallBack) {

    inner class ChooseIconViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = IconItemLayoutBinding.bind(view)

        private var imageId: Int = 0

        init {
            binding.icon.setOnClickListener {
                onIconClick(imageId)
            }
        }

        fun bind(imageId: Int) {
            this.imageId = imageId
            binding.icon.setImageResource(imageId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseIconViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.icon_item_layout, parent, false)
        return ChooseIconViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ChooseIconViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

object IconDiffCallBack : DiffUtil.ItemCallback<Int>() {
    override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }

}