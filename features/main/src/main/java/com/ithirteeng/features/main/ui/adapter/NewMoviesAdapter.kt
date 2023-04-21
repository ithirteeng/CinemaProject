package com.ithirteeng.features.main.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ithirteeng.features.main.R
import com.ithirteeng.features.main.databinding.InTrendItemLayoutBinding
import com.ithirteeng.shared.movies.entity.MovieEntity

class NewMoviesAdapter(private val onMovieClick: (movieEntity: MovieEntity) -> Unit) :
    ListAdapter<MovieEntity, NewMoviesAdapter.NewMoviesViewHolder>(MovieDiffCallBack) {

    inner class NewMoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = InTrendItemLayoutBinding.bind(view)

        private lateinit var movieEntity: MovieEntity

        init {
            binding.posterImageView.setOnClickListener {
                onMovieClick(movieEntity)
            }
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(movieEntity: MovieEntity) {
            this.movieEntity = movieEntity
            Glide
                .with(binding.root)
                .load(movieEntity.poster)
                .placeholder(binding.root.context.getDrawable(com.ithirteeng.component.design.R.drawable.image_placeholder))
                .error(binding.root.context.getDrawable(com.ithirteeng.component.design.R.drawable.image_placeholder))
                .into(binding.posterImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewMoviesViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.new_item_layout, parent, false)

        return NewMoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewMoviesViewHolder, position: Int) {
        val movieEntity = getItem(position)
        holder.bind(movieEntity)
    }


}