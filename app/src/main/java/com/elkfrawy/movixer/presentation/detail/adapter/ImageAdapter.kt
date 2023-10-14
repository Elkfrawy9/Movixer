package com.elkfrawy.movixer.presentation.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elkfrawy.movixer.databinding.ImageItemBinding
import com.elkfrawy.movixer.databinding.VideoItemBinding
import com.elkfrawy.movixer.domain.model.Image
import com.elkfrawy.movixer.domain.model.Video
import com.elkfrawy.movixer.presentation.utlis.loadImage

class ImageAdapter(val context: Context, val listener: OnImageClicked): RecyclerView.Adapter<ImageAdapter.ImageViewHolder>(){

    var images: List<Image> = ArrayList<Image>()

    fun submitList(images: List<Image>){
        this.images = images
        notifyDataSetChanged()
    }

    class ImageViewHolder(val binding: ImageItemBinding): RecyclerView.ViewHolder(binding.root){}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(context), parent, false)
        binding.root.setOnClickListener {
            listener.setOnImageClickedListener(images)
        }
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = images[position]
        loadImage(context, holder.binding.personImg, image.file_path)
    }

    override fun getItemCount(): Int = images.size

    interface OnImageClicked{
        fun setOnImageClickedListener(images: List<Image>)
    }
}