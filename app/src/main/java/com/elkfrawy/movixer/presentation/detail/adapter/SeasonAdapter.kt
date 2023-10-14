package com.elkfrawy.movixer.presentation.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elkfrawy.movixer.databinding.ReviewItemBinding
import com.elkfrawy.movixer.databinding.SeasonItemBinding
import com.elkfrawy.movixer.domain.model.Review
import com.elkfrawy.movixer.domain.model.Season
import com.elkfrawy.movixer.presentation.utlis.loadImage

class SeasonAdapter(val context: Context): ListAdapter<Season, SeasonAdapter.SeasonViewHolder>(
    comparator){

    class SeasonViewHolder(val binding: SeasonItemBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        val binding = SeasonItemBinding.inflate(LayoutInflater.from(context), parent, false)
        val holder = SeasonViewHolder(binding)

        return holder
    }

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        val season = getItem(position)

        holder.binding.apply {
            loadImage(context, seasonImg, season.poster_path)
            seasonTxt.text = season.name
            seTxt.text = "S${season.season_number} E${season.episode_count}"
        }
    }

    companion object{
        val comparator = object : DiffUtil.ItemCallback<Season>(){
            override fun areItemsTheSame(oldItem: Season, newItem: Season): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Season, newItem: Season): Boolean {
                return oldItem == newItem
            }
        }
    }
}