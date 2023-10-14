package com.elkfrawy.movixer.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elkfrawy.movixer.databinding.CarouselViewBinding
import com.elkfrawy.movixer.domain.model.Movie
import com.elkfrawy.movixer.domain.model.Series
import com.elkfrawy.movixer.presentation.utlis.SERIES_ADAPTER
import com.elkfrawy.movixer.presentation.utlis.loadImage

class CarouselAdapter(val data: String, val context: Context, val listener: OnItemClick) :
    RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    var series: List<Series> = ArrayList<Series>()
    var movies: List<Movie> = ArrayList<Movie>()

    class CarouselViewHolder(val binding: CarouselViewBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val binding = CarouselViewBinding.inflate(LayoutInflater.from(context), parent, false)
        val holder = CarouselViewHolder(binding)

        return holder
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {

        holder.binding.carouselImg.setOnClickListener {
            if (data == SERIES_ADAPTER)
                listener.setOnSeriesItemClickedListener(series.get(position).id)
            else
                listener.setOnMovieItemClickedListener(movies.get(position).id)
        }

        if (data == SERIES_ADAPTER) {
            val show = series.get(position)
            loadImage(context, holder.binding.carouselImg, show.poster_path)

        } else {
            val movie = movies.get(position)
            loadImage(context, holder.binding.carouselImg, movie.poster_path)
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

    interface OnItemClick {
        fun setOnMovieItemClickedListener(movieId: Int) {}
        fun setOnSeriesItemClickedListener(seriesId: Int) {}
    }
}