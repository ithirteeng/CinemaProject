package com.ithirteeng.features.collections.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ithirteeng.features.collections.R
import com.ithirteeng.features.collections.databinding.MovieItemLayoutBinding
import com.ithirteeng.shared.movies.entity.MovieEntity

class CollectionMoviesAdapter(
    private val onMovieClick: (movieEntity: MovieEntity) -> Unit,
) : ListAdapter<MovieEntity, CollectionMoviesAdapter.CollectionMoviesViewHolder>(MovieDiffCallBack) {

    inner class CollectionMoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = MovieItemLayoutBinding.bind(view)
        private lateinit var movieEntity: MovieEntity

        init {
            binding.clickableView.setOnClickListener {
                onMovieClick(movieEntity)
            }
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(movieEntity: MovieEntity) {
            this.movieEntity = movieEntity
            binding.movieName.text = movieEntity.name
            binding.movieDescription.text = movieEntity.description
            Glide
                .with(binding.root)
                .load(movieEntity.poster)
                .placeholder(binding.root.context.getDrawable(com.ithirteeng.component.design.R.drawable.image_placeholder))
                .error(binding.root.context.getDrawable(com.ithirteeng.component.design.R.drawable.image_placeholder))
                .into(binding.imageView)
        }

    }

    override fun onBindViewHolder(holder: CollectionMoviesViewHolder, position: Int) {
        val entity = getItem(position)
        holder.bind(entity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionMoviesViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.movie_item_layout, parent, false)
        return CollectionMoviesViewHolder(view)
    }
}

object MovieDiffCallBack : DiffUtil.ItemCallback<MovieEntity>() {
    override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
        oldItem == newItem

}