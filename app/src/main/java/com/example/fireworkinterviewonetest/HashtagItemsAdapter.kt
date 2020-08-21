package com.example.fireworkinterviewonetest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.hashtag_item.view.*

class HashtagItemsAdapter : ListAdapter<HashTagItem, HashtagItemViewHolder>(
    object : DiffUtil.ItemCallback<HashTagItem>() {
        override fun areItemsTheSame(oldItem: HashTagItem, newItem: HashTagItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HashTagItem, newItem: HashTagItem): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HashtagItemViewHolder {
        return HashtagItemViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.hashtag_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HashtagItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class HashtagItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(hashtagItem: HashTagItem) {
        itemView.hashtag_name.text = hashtagItem.name
    }

}