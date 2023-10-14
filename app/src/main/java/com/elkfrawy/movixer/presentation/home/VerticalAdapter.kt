package com.elkfrawy.movixer.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elkfrawy.movixer.databinding.MovieCardVerticalBinding
import com.elkfrawy.movixer.domain.model.Genre
import com.elkfrawy.movixer.domain.model.Movie
import com.elkfrawy.movixer.domain.model.Series
import com.elkfrawy.movixer.presentation.utlis.SERIES_ADAPTER
import com.elkfrawy.movixer.presentation.utlis.genreMovie
import com.elkfrawy.movixer.presentation.utlis.genreTV
import com.elkfrawy.movixer.presentation.utlis.loadImage
import com.elkfrawy.movixer.presentation.utlis.popularityFormat

class VerticalAdapter(val data: String, val context: Context, val listener: OnVerticalItemClick) :
    RecyclerView.Adapter<VerticalAdapter.VerticalViewHolder>() {

    var series: List<Series> = ArrayList<Series>()
    var movies: List<Movie> = ArrayList<Movie>()

    class VerticalViewHolder(val binding: MovieCardVerticalBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalViewHolder {
        val binding = MovieCardVerticalBinding.inflate(LayoutInflater.from(context), parent, false)
        val holder = VerticalViewHolder(binding)


        return holder
    }

    fun seriesList(series: List<Series>) {
        this.series = series
        notifyDataSetChanged()
    }

    fun moviesList(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: VerticalViewHolder, position: Int) {

        holder.binding.root.setOnClickListener {
            if (data == SERIES_ADAPTER)
                listener.setOnVSeriesItemClickedListener(series.get(position).id)
            else
                listener.setOnVMovieItemClickedListener(movies.get(position).id)
        }

        if (data == SERIES_ADAPTER) {
            val show = series.get(position)
            val genera = getGenera(show.genre_ids, genreTV)
            bindSeriesView(holder.binding, show, genera)
        } else {
            val movie = movies.get(position)
            val genera = getGenera(movie.genre_ids, genreMovie)
            bindMovieView(holder.binding, movie, genera)
        }

    }

    private fun getGenera(li: List<Int>, li2: List<Genre>): String {
        var genera = ""
        for (item in li) {
            for (g in li2) {
                if (item == g.id) {
                    genera += "${g.name}, "
                }
            }
        }
        return genera
    }

    override fun getItemCount(): Int {
        return if (data == SERIES_ADAPTER) series.size else movies.size
    }

    private fun bindMovieView(binding: MovieCardVerticalBinding, movie: Movie, genera: String) {
        binding.apply {
            movieTitle.text = movie.title
            movieDate.text = movie.release_date
            movieTxtVote.text = popularityFormat(movie.popularity)
            movieTxtRate.text = movie.vote_average.toString()
            movieRate.rating = movie.vote_average / 2
            movieGenera.text = genera
            loadImage(context, movieImg, movie.poster_path)
        }
    }

    private fun bindSeriesView(binding: MovieCardVerticalBinding, series: Series, genera: String) {
        binding.apply {
            movieTitle.text = series.name
            movieDate.text = series.first_air_date
            movieTxtVote.text = popularityFormat(series.popularity)
            movieTxtRate.text = series.vote_average.toString()
            movieRate.rating = series.vote_average / 2
            movieGenera.text = genera
            loadImage(context, movieImg, series.poster_path)
        }
    }

    interface OnVerticalItemClick {
        fun setOnVMovieItemClickedListener(movieId: Int) {}
        fun setOnVSeriesItemClickedListener(seriesId: Int) {}
    }
}