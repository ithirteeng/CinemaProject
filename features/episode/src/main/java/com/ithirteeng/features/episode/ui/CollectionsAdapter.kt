package com.ithirteeng.features.episode.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ithirteeng.features.episode.R
import com.ithirteeng.features.episode.databinding.TestCollectionItemLayputBinding
import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity

class CollectionsAdapter(
    private val onCollectionClick: (localCollectionEntity: LocalCollectionEntity) -> Unit,
) : ListAdapter<LocalCollectionEntity, CollectionsAdapter.CollectionsViewHolder>(CollectionCallBack) {

    inner class CollectionsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = TestCollectionItemLayputBinding.bind(view)

        private lateinit var entity: LocalCollectionEntity

        init {
            binding.collectionButton.setOnClickListener {
                onCollectionClick(this.entity)
            }
        }

        fun bind(localCollectionEntity: LocalCollectionEntity) {
            entity = localCollectionEntity
            binding.collectionButton.text = entity.collectionName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionsViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.test_collection_item_layput, parent, false)
        return CollectionsViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CollectionsViewHolder, position: Int) {
        val entity = getItem(position)
        holder.bind(entity)
    }
}

object CollectionCallBack : DiffUtil.ItemCallback<LocalCollectionEntity>() {
    override fun areItemsTheSame(
        oldItem: LocalCollectionEntity,
        newItem: LocalCollectionEntity,
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: LocalCollectionEntity,
        newItem: LocalCollectionEntity,
    ): Boolean {
        return oldItem.collectionId == newItem.collectionId
    }

}