package com.elkfrawy.movixer.presentation.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elkfrawy.movixer.databinding.CastItemBinding
import com.elkfrawy.movixer.databinding.VideoItemBinding
import com.elkfrawy.movixer.domain.model.Cast
import com.elkfrawy.movixer.domain.model.Video
import com.elkfrawy.movixer.presentation.utlis.loadUserImage

class CastAdapter(val context: Context, val listener: OnPersonClicked): ListAdapter<Cast, CastAdapter.CastViewHolder>(comparator) {

    class CastViewHolder(val binding: CastItemBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val binding = CastItemBinding.inflate(LayoutInflater.from(context), parent, false)
        val holder = CastViewHolder(binding)

        return holder
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val cast = getItem(position)
        holder.binding.root.setOnClickListener { listener.setOnPersonClickedListener(cast.id) }
        holder.binding.apply {
            loadUserImage(context, artistImg, cast.profile_path)
            character.text = cast.character
            artistName.text = cast.name
        }
    }

    companion object{
        val comparator = object : DiffUtil.ItemCallback<Cast>(){
            override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnPersonClicked{
        fun setOnPersonClickedListener(personId: Int)
    }
}