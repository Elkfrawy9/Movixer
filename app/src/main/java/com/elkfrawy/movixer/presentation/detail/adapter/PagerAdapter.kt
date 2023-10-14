package com.elkfrawy.movixer.presentation.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elkfrawy.movixer.databinding.ImagePagerItemBinding
import com.elkfrawy.movixer.domain.model.Image
import com.elkfrawy.movixer.presentation.utlis.loadImage

class PagerAdapter(val context: Context, val list: List<Image>): RecyclerView.Adapter<PagerAdapter.PagerViewHolder>() {

    class PagerViewHolder(val binding: ImagePagerItemBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val binding = ImagePagerItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return PagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val image = list[position]
        loadImage(context, holder.binding.img, image.file_path)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}