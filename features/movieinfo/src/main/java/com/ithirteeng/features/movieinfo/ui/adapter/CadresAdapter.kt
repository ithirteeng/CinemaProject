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
import com.ithirteeng.features.movieinfo.databinding.CadreItemLayoutBinding
import com.ithirteeng.shared.movies.entity.MovieEntity

class CadresAdapter :
    ListAdapter<MovieEntity, CadresAdapter.CadresViewHolder>(MovieDiffCallBack) {

    inner class CadresViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CadreItemLayoutBinding.bind(view)

        private lateinit var movieEntity: MovieEntity

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CadresViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.cadre_item_layout, parent, false)

        return CadresViewHolder(view)
    }

    override fun onBindViewHolder(holder: CadresViewHolder, position: Int) {
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