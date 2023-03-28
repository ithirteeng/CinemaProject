package com.ithirteeng.features.compilation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ithirteeng.features.compilation.R
import com.ithirteeng.shared.movies.entity.MovieEntity

class CardStackAdapter :
    ListAdapter<String, CardStackAdapter.CardStackViewHolder>(TestDiffCallBack) {
    inner class CardStackViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardStackViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item_layout, parent, false)
        return CardStackViewHolder((view))
    }

    override fun onBindViewHolder(holder: CardStackViewHolder, position: Int) {

    }
}

object TestDiffCallBack : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}

object MovieDiffCallBack : DiffUtil.ItemCallback<MovieEntity>() {
    override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
        oldItem == newItem

}