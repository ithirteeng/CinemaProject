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

class CadresAdapter :
    ListAdapter<String, CadresAdapter.CadresViewHolder>(MovieDiffCallBack) {

    inner class CadresViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CadreItemLayoutBinding.bind(view)

        private lateinit var movieImageUrl: String

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(url: String) {
            this.movieImageUrl = url
            Glide
                .with(binding.root)
                .load(url)
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
        val imageUrl = getItem(position)
        holder.bind(imageUrl)
    }


}

object MovieDiffCallBack : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
        oldItem == newItem


    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
        oldItem == newItem

}