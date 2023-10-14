package com.elkfrawy.movixer.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elkfrawy.movixer.databinding.MovieCardViewBinding
import com.elkfrawy.movixer.domain.model.Movie
import com.elkfrawy.movixer.domain.model.Series
import com.elkfrawy.movixer.presentation.utlis.SERIES_ADAPTER
import com.elkfrawy.movixer.presentation.utlis.loadImage
import com.elkfrawy.movixer.presentation.utlis.popularityFormat

class HorizontalAdapter(val data: String, val context: Context, val listener: OnHorizontalItemClick) :
    RecyclerView.Adapter<HorizontalAdapter.HorizontalViewHolder>() {

    var series: List<Series> = ArrayList<Series>()
    var movies: List<Movie> = ArrayList<Movie>()

    class HorizontalViewHolder(val binding: MovieCardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalViewHolder {
        val binding = MovieCardViewBinding.inflate(LayoutInflater.from(context), parent, false)
        val holder = HorizontalViewHolder(binding)

        return holder
    }

    override fun onBindViewHolder(holder: HorizontalViewHolder, position: Int) {

        holder.binding.root.setOnClickListener {
            if (data == SERIES_ADAPTER)
                listener.setOnHSeriesItemClickedListener(series.get(position).id)
            else
                listener.setOnHMovieItemClickedListener(movies.get(position).id)
        }


        if (data == SERIES_ADAPTER) {
            val show = series.get(position)
            bindSeriesData(holder.binding, show)

        } else {
            val movie = movies.get(position)
            bindMovieData(holder.binding, movie)
        }


    }

    fun bindMovieData(binding: MovieCardViewBinding, movie: Movie) {
        binding.apply {

            movieTitle.text = movie.title
            movieDate.text = movie.release_date
            rateTxt.text = "${movie.vote_average}"
            popularityTitle.text = popularityFormat(movie.popularity)
            loadImage(context, movieImg, movie.backdrop_path)

        }
    }

    fun bindSeriesData(binding: MovieCardViewBinding, show: Series) {
        binding.apply {

            movieTitle.text = show.name
            movieDate.text = show.first_air_date
            rateTxt.text = "${show.vote_average}"
            popularityTitle.text = popularityFormat(show.popularity)
            loadImage(context, movieImg, show.backdrop_path)

        }
    }

    override fun getItemCount(): Int {
        return if (data == SERIES_ADAPTER) series.size else movies.size
    }

    fun seriesList(series: List<Series>) {
        this.series = series
        notifyDataSetChanged()
    }

    fun moviesList(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    interface OnHorizontalItemClick {
        fun setOnHMovieItemClickedListener(movieId: Int) {}
        fun setOnHSeriesItemClickedListener(seriesId: Int) {}
    }
}