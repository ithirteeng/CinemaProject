package com.ithirteeng.features.episode.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ithirteeng.features.episode.R
import com.ithirteeng.features.episode.databinding.TestCollectionItemLayputBinding
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity

class CollectionsAdapter(
    private val onCollectionClick: (collectionEntity: CollectionEntity) -> Unit,
) : ListAdapter<CollectionEntity, CollectionsAdapter.CollectionsViewHolder>(CollectionCallBack) {

    inner class CollectionsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = TestCollectionItemLayputBinding.bind(view)

        private lateinit var entity: CollectionEntity

        init {
            binding.collectionButton.setOnClickListener {
                onCollectionClick(this.entity)
            }
        }

        fun bind(collectionEntity: CollectionEntity) {
            entity = collectionEntity
            binding.collectionButton.text = entity.name
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

object CollectionCallBack : DiffUtil.ItemCallback<CollectionEntity>() {
    override fun areItemsTheSame(
        oldItem: CollectionEntity,
        newItem: CollectionEntity,
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: CollectionEntity,
        newItem: CollectionEntity,
    ): Boolean {
        return oldItem.id == newItem.id
    }

}