package com.elkfrawy.movixer.presentation.seeAll

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.elkfrawy.movixer.databinding.MovieCardVerticalBinding
import com.elkfrawy.movixer.domain.model.Movie
import com.elkfrawy.movixer.domain.model.Series
import com.elkfrawy.movixer.presentation.utlis.genreMovie
import com.elkfrawy.movixer.presentation.utlis.loadImage
import com.elkfrawy.movixer.presentation.utlis.popularityFormat

class SeeAllSeriesAdapter(val context: Context, val listener: OnSeeAllSeriesItemClick): PagingDataAdapter<Series, SeeAllSeriesAdapter.SeeAllViewHolder>(comparator) {

    class SeeAllViewHolder(val binding: MovieCardVerticalBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeeAllViewHolder {
        val binding = MovieCardVerticalBinding.inflate(LayoutInflater.from(context), parent, false)
        val holder = SeeAllViewHolder(binding)

        return holder
    }

    override fun onBindViewHolder(holder: SeeAllViewHolder, position: Int) {
        holder.binding.root.setOnClickListener {
            listener.setOnSeeAllSeriesClickedListener(getItem(position)?.id!!)
        }

        val show = getItem(position)
        var genera = ""
        for (item in show!!.genre_ids){
            for (g in genreMovie){
                if (item == g.id){
                    genera += "${g.name}, "
                }
            }
        }

        holder.binding.apply {
            movieTitle.text = show.name
            movieDate.text = show.first_air_date
            movieTxtVote.text = popularityFormat(show.popularity)
            movieTxtRate.text = show.vote_average.toString()
            movieRate.rating = show.vote_average / 2
            movieGenera.text = genera
            loadImage(context, movieImg, show.poster_path)
        }
    }

    companion object{
        val comparator = object : DiffUtil.ItemCallback<Series>(){
            override fun areItemsTheSame(oldItem: Series, newItem: Series): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Series, newItem: Series): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnSeeAllSeriesItemClick {
        fun setOnSeeAllSeriesClickedListener(seriesId: Int)
    }

}