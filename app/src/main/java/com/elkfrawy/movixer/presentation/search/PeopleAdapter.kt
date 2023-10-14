package com.elkfrawy.movixer.presentation.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.elkfrawy.movixer.databinding.MovieCardVerticalBinding
import com.elkfrawy.movixer.databinding.PeopleItemBinding
import com.elkfrawy.movixer.domain.model.Movie
import com.elkfrawy.movixer.domain.model.People
import com.elkfrawy.movixer.presentation.utlis.loadUserImage

class PeopleAdapter(val context: Context, val listener: OnPersonClickListener): PagingDataAdapter<People, PeopleAdapter.PeopleViewHolder>(comparator){

    class PeopleViewHolder(val binding: PeopleItemBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        val person = getItem(position)
        holder.binding.root.setOnClickListener {
            listener.setOnPersonClickedListener(person?.id!!)
        }
        holder.binding.apply {
            loadUserImage(context, personImg, person?.profile_path)
            personName.text = person?.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val binding = PeopleItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return PeopleViewHolder(binding)
    }

    companion object{
        val comparator = object : DiffUtil.ItemCallback<People>(){
            override fun areItemsTheSame(oldItem: People, newItem: People): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: People, newItem: People): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnPersonClickListener{
        fun setOnPersonClickedListener(personId: Int)
    }
}