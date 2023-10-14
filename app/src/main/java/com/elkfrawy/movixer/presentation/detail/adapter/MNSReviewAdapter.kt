package com.elkfrawy.movixer.presentation.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.elkfrawy.movixer.databinding.ReviewItemBinding
import com.elkfrawy.movixer.domain.model.Review
import com.elkfrawy.movixer.presentation.utlis.loadUserImage

class MNSReviewAdapter(val context: Context) :
    PagingDataAdapter<Review, MNSReviewAdapter.SeriesReviewViewHolder>(comparator) {

    class SeriesReviewViewHolder(val binding: ReviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {}


    override fun onBindViewHolder(holder: SeriesReviewViewHolder, position: Int) {
        val review = getItem(position)
        holder.binding.apply {
            authorName.text = review?.author ?: "User"
            authorDate.text = review?.created_at
            authorRate.text = "${review?.author_details?.rating ?: 0.0}"
            authorContent.text = review?.content
            loadUserImage(context, authorImg, review?.author_details?.avatar_path)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesReviewViewHolder {
        val binding = ReviewItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return SeriesReviewViewHolder(binding)
    }

    companion object {

        val comparator = object : DiffUtil.ItemCallback<Review>() {
            override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
                return oldItem == newItem
            }
        }

    }

}