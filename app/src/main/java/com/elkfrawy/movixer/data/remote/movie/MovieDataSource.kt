package com.elkfrawy.movixer.data.remote.movie

import com.elkfrawy.movixer.data.model.MovieResponse
import com.elkfrawy.movixer.domain.model.Cast
import com.elkfrawy.movixer.domain.model.Movie
import com.elkfrawy.movixer.domain.model.MovieDetails
import com.elkfrawy.movixer.domain.model.Review
import com.elkfrawy.movixer.domain.model.Video
import com.elkfrawy.movixer.domain.utils.Result
import retrofit2.Response

interface MovieDataSource {

    suspend fun getNowPlaying(page: Int):Result<List<Movie>>
    suspend fun getPopular(page: Int):Result<List<Movie>>
    suspend fun getTopRated(page: Int):Result<List<Movie>>
    suspend fun getUpComing(page: Int):Result<List<Movie>>
    suspend fun getMovieDetails(movieId: Int): Result<MovieDetails>
    suspend fun getMovieRecommendation(movieId: Int, page: Int):Result<List<Movie>>
    suspend fun getMovieSimilar(movieId: Int, page: Int):Result<List<Movie>>
    suspend fun getMovieReviews(movieId: Int, page: Int):Result<List<Review>>
    suspend fun getMovieCasts(movieId: Int):Result<List<Cast>>
    suspend fun getMovieVideo(movieId: Int):Result<List<Video>>


}