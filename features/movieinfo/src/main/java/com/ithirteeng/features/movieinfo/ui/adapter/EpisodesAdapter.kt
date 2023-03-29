package com.ithirteeng.features.movieinfo.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ithirteeng.features.movieinfo.R
import com.ithirteeng.features.movieinfo.databinding.EpisodeItemLayoutBinding
import com.ithirteeng.shared.movies.entity.EpisodeEntity

class EpisodesAdapter(private val onEpisodeClick: (episodeId: String) -> Unit) :
    ListAdapter<EpisodeEntity, EpisodesAdapter.EpisodesViewHolder>(EpisodesEntityDiffCallback) {

    inner class EpisodesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = EpisodeItemLayoutBinding.bind(view)

        private lateinit var episodeEntity: EpisodeEntity

        init {
            binding.clickableView.setOnClickListener {
                onEpisodeClick(episodeEntity.episodeId)
            }
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(episodeEntity: EpisodeEntity) {
            this.episodeEntity = episodeEntity
            binding.episodeNameTextView.text = episodeEntity.name
            binding.episodeDescriptionTextView.text = episodeEntity.description
            binding.episodeYearTextView.text = episodeEntity.year
            Glide
                .with(binding.root)
                .load(this.episodeEntity.preview)
                .placeholder(binding.root.context.getDrawable(com.ithirteeng.component.design.R.drawable.image_placeholder))
                .error(binding.root.context.getDrawable(com.ithirteeng.component.design.R.drawable.image_placeholder))
                .into(binding.posterImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.episode_item_layout, parent, false)

        return EpisodesViewHolder(view)
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        val episodeEntity = getItem(position)
        holder.bind(episodeEntity)
    }


}

object EpisodesEntityDiffCallback : DiffUtil.ItemCallback<EpisodeEntity>() {
    override fun areItemsTheSame(oldItem: EpisodeEntity, newItem: EpisodeEntity): Boolean =
        oldItem.episodeId == newItem.episodeId


    override fun areContentsTheSame(oldItem: EpisodeEntity, newItem: EpisodeEntity): Boolean =
        oldItem == newItem

}