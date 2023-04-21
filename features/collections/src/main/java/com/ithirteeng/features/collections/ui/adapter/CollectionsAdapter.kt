package com.ithirteeng.features.collections.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ithirteeng.features.collections.R
import com.ithirteeng.features.collections.databinding.CollectionItemLayoutBinding
import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity

class CollectionsAdapter(private val onCollectionClick: (collectionEntity: LocalCollectionEntity) -> Unit) :
    ListAdapter<LocalCollectionEntity, CollectionsAdapter.CollectionsViewHolder>(
        CollectionsDiffCallback
    ) {

    inner class CollectionsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = CollectionItemLayoutBinding.bind(view)

        private lateinit var collectionEntity: LocalCollectionEntity

        init {
            binding.clickableView.setOnClickListener {
                onCollectionClick(collectionEntity)
            }
        }

        fun bind(collectionEntity: LocalCollectionEntity) {
            this.collectionEntity = collectionEntity
            binding.collectionNameTextView.text = collectionEntity.collectionName
            binding.collectionIconImageView.setImageResource(collectionEntity.collectionImageId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionsViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.collection_item_layout, parent, false)
        return CollectionsViewHolder((view))
    }

    override fun onBindViewHolder(holder: CollectionsViewHolder, position: Int) {
        val entity = getItem(position)
        holder.bind(entity)
    }
}

object CollectionsDiffCallback : DiffUtil.ItemCallback<LocalCollectionEntity>() {
    override fun areItemsTheSame(
        oldItem: LocalCollectionEntity,
        newItem: LocalCollectionEntity,
    ): Boolean =
        oldItem.collectionId == newItem.collectionId


    override fun areContentsTheSame(
        oldItem: LocalCollectionEntity,
        newItem: LocalCollectionEntity,
    ): Boolean =
        oldItem == newItem

}