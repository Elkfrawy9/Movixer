package com.elkfrawy.movixer.presentation.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elkfrawy.movixer.databinding.ReviewItemBinding
import com.elkfrawy.movixer.databinding.VideoItemBinding
import com.elkfrawy.movixer.domain.model.Review
import com.elkfrawy.movixer.domain.model.Video
import com.elkfrawy.movixer.presentation.utlis.loadUserImage
import java.util.concurrent.CountDownLatch

class ReviewAdapter(val context: Context, val count: Int): ListAdapter<Review, ReviewAdapter.SeasonViewHolder>(
    comparator){

    class SeasonViewHolder(val binding: ReviewItemBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        val binding = ReviewItemBinding.inflate(LayoutInflater.from(context), parent, false)
        val holder = SeasonViewHolder(binding)

        return holder
    }

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        val review = getItem(position)

        holder.binding.apply {
            loadUserImage(context, authorImg, review.author_details.avatar_path)
            authorName.text = review.author
            authorRate.text = "${review.author_details.rating}"
            authorContent.text = review.content
            authorDate.text = review.created_at.split("T")[0]
        }
    }

    override fun getItemCount(): Int = count

    companion object{
        val comparator = object : DiffUtil.ItemCallback<Review>(){
            override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
                return oldItem == newItem
            }
        }
    }
}