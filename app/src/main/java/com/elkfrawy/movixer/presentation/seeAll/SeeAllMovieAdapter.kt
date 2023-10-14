package com.elkfrawy.movixer.presentation.seeAll

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.elkfrawy.movixer.databinding.MovieCardVerticalBinding
import com.elkfrawy.movixer.domain.model.Movie
import com.elkfrawy.movixer.presentation.utlis.genreMovie
import com.elkfrawy.movixer.presentation.utlis.loadImage
import com.elkfrawy.movixer.presentation.utlis.popularityFormat
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class SeeAllMovieAdapter(val context: Context, val listener: OnSeeAllMoviesItemClick): PagingDataAdapter<Movie, SeeAllMovieAdapter.SeeAllViewHolder>(comparator) {

    class SeeAllViewHolder(val binding: MovieCardVerticalBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeeAllViewHolder {
        val binding = MovieCardVerticalBinding.inflate(LayoutInflater.from(context), parent, false)
        val holder = SeeAllViewHolder(binding)

        return holder
    }

    override fun onBindViewHolder(holder: SeeAllViewHolder, position: Int) {

        val movie = getItem(position)
        holder.binding.root.setOnClickListener {
            listener.setOnSeeAllMoviesClickedListener(movie?.id!!)
        }
        var genera = ""
        for (item in movie!!.genre_ids){
            for (g in genreMovie){
                if (item == g.id){
                    genera += "${g.name}, "
                }
            }
        }

        holder.binding.apply {
            movieTitle.text = movie.title
            movieDate.text = movie.release_date
            movieTxtVote.text = popularityFormat(movie.popularity)
            movieTxtRate.text = movie.vote_average.toString()
            movieRate.rating = movie.vote_average / 2
            movieGenera.text = genera
            loadImage(context, movieImg, movie.poster_path)
        }
    }

    companion object{
        val comparator = object : DiffUtil.ItemCallback<Movie>(){
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnSeeAllMoviesItemClick {
        fun setOnSeeAllMoviesClickedListener(movieId: Int)
    }

}