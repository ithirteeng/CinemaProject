package com.ithirteeng.features.main.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ithirteeng.features.main.R
import com.ithirteeng.features.main.databinding.RecentItemLayoutBinding
import com.ithirteeng.shared.movies.entity.MovieEntity

class RecentViewedMoviesAdapter(private val onPlayButtonClick: (movieEntity: MovieEntity) -> Unit) :
    ListAdapter<MovieEntity, RecentViewedMoviesAdapter.RecentViewedMoviesViewHolder>(
        MovieDiffCallBack
    ) {

    inner class RecentViewedMoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RecentItemLayoutBinding.bind(view)

        private lateinit var movieEntity: MovieEntity

        init {
            binding.playButton.setOnClickListener {
                onPlayButtonClick(movieEntity)
            }
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(movieEntity: MovieEntity) {
            this.movieEntity = movieEntity
            binding.movieNameTextView.text = movieEntity.name
            Glide
                .with(binding.root)
                .load(movieEntity.poster)
                .placeholder(binding.root.context.getDrawable(com.ithirteeng.component.design.R.drawable.image_placeholder))
                .error(binding.root.context.getDrawable(com.ithirteeng.component.design.R.drawable.image_placeholder))
                .into(binding.posterImageView)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecentViewedMoviesViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recent_item_layout, parent, false)

        return RecentViewedMoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecentViewedMoviesViewHolder, position: Int) {
        val movieEntity = getItem(position)
        holder.bind(movieEntity)
    }
}