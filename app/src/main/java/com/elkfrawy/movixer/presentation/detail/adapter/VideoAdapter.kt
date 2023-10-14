package com.elkfrawy.movixer.presentation.detail.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elkfrawy.movixer.databinding.VideoItemBinding
import com.elkfrawy.movixer.domain.model.Video
import com.elkfrawy.movixer.presentation.utlis.loadVideoThumbnail

class VideoAdapter(val context: Context): ListAdapter<Video, VideoAdapter.VideoViewHolder>(
    comparator){

    class VideoViewHolder(val binding: VideoItemBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = VideoItemBinding.inflate(LayoutInflater.from(context), parent, false)
        val holder =VideoViewHolder(binding)

        return holder
    }


    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = getItem(position)
        holder.binding.root.setOnClickListener {
            if (video.id.isEmpty() || video.id != null){
                val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:${video.key}"))
                val webIntent = Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=${video.key}"))
                if (appIntent.resolveActivity(context.packageManager) != null)
                    context.startActivity(appIntent)
                else
                    context.startActivity(webIntent)
            }
        }
        loadVideoThumbnail(context, holder.binding.videoThumbnail, video.key)

    }

    companion object{
        val comparator = object : DiffUtil.ItemCallback<Video>(){
            override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
                return oldItem == newItem
            }
        }
    }



}