package com.ithirteeng.features.main.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ithirteeng.shared.movies.entity.MovieEntity


object MovieDiffCallBack : DiffUtil.ItemCallback<MovieEntity>() {
    override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
        oldItem == newItem

}