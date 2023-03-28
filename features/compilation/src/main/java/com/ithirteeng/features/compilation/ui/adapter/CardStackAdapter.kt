package com.ithirteeng.features.compilation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ithirteeng.features.compilation.R
import com.ithirteeng.features.compilation.databinding.CardItemLayoutBinding
import com.ithirteeng.shared.movies.entity.MovieEntity

class CardStackAdapter :
    ListAdapter<MovieEntity, CardStackAdapter.CardStackViewHolder>(MovieDiffCallBack) {
    inner class CardStackViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CardItemLayoutBinding.bind(view)

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(movieEntity: MovieEntity) {
            Glide
                .with(binding.root)
                .load(movieEntity.poster)
                .placeholder(binding.root.context.getDrawable(com.ithirteeng.component.design.R.drawable.image_placeholder))
                .error(binding.root.context.getDrawable(com.ithirteeng.component.design.R.drawable.image_placeholder))
                .into(binding.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardStackViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item_layout, parent, false)
        return CardStackViewHolder((view))
    }

    override fun onBindViewHolder(holder: CardStackViewHolder, position: Int) {
        val movieEntity = getItem(position)
        holder.bind(movieEntity)
    }
}

object MovieDiffCallBack : DiffUtil.ItemCallback<MovieEntity>() {
    override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
        oldItem == newItem

}