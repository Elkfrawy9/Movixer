package com.elkfrawy.movixer.data

import com.elkfrawy.movixer.data.model.CastResponse
import com.elkfrawy.movixer.data.model.ImageResponse
import com.elkfrawy.movixer.data.model.MovieResponse
import com.elkfrawy.movixer.data.model.PeopleResponse
import com.elkfrawy.movixer.data.model.ReviewResponse
import com.elkfrawy.movixer.data.model.SeriesResponse
import com.elkfrawy.movixer.data.model.VideoResponse
import com.elkfrawy.movixer.domain.model.MovieDetails
import com.elkfrawy.movixer.domain.model.PeopleDetail
import com.elkfrawy.movixer.domain.model.SeriesDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(@Query("page") page: Int): Response<MovieResponse>

    @GET("movie/popular")
    suspend fun getPopular(@Query("page") page: Int): Response<MovieResponse>

    @GET("movie/top_rated")
    suspend fun getTopRated(@Query("page") page: Int): Response<MovieResponse>

    @GET("movie/upcoming")
    suspend fun getUpComing(@Query("page") page: Int): Response<MovieResponse>

    @GET("movie/{id}")
    suspend fun getMovieDetails(@Path("id") movieId: Int): Response<MovieDetails>

    @GET("movie/{id}/credits")
    suspend fun getMovieCasts(@Path("id") movieId: Int): Response<CastResponse>

    @GET("movie/{id}/recommendations")
    suspend fun getMovieRecommendation(@Path("id") movieId: Int, @Query("page") page: Int): Response<MovieResponse>

    @GET("movie/{id}/similar")
    suspend fun getMovieSimilar(@Path("id") movieId: Int, @Query("page") page: Int): Response<MovieResponse>

    @GET("movie/{id}/reviews")
    suspend fun getMovieReviews(@Path("id") movieId: Int, @Query("page") page: Int): Response<ReviewResponse>

    @GET("movie/{id}/videos")
    suspend fun getMovieVideos(@Path("id") movieId: Int): Response<VideoResponse>




    @GET("tv/airing_today")
    suspend fun getAiringToday(@Query("page") page: Int): Response<SeriesResponse>

    @GET("tv/on_the_air")
    suspend fun getOnTheAir(@Query("page") page: Int): Response<SeriesResponse>

    @GET("tv/popular")
    suspend fun getSeriesPopular(@Query("page") page: Int): Response<SeriesResponse>

    @GET("tv/top_rated")
    suspend fun getSeriesTopRated(@Query("page") page: Int): Response<SeriesResponse>

    @GET("tv/{id}")
    suspend fun getSeriesDetails(@Path("id") seriesId: Int): Response<SeriesDetails>

    @GET("tv/{id}/credits")
    suspend fun getSeriesCasts(@Path("id") seriesId: Int): Response<CastResponse>

    @GET("tv/{id}/recommendations")
    suspend fun getSeriesRecommendation(@Path("id") seriesId: Int, @Query("page") page: Int): Response<SeriesResponse>

    @GET("tv/{id}/reviews")
    suspend fun getSeriesReviews(@Path("id") seriesId: Int, @Query("page") page: Int): Response<ReviewResponse>

    @GET("tv/{id}/videos")
    suspend fun getSeriesVideos(@Path("id") seriesId: Int):Response<VideoResponse>

    @GET("search/movie")
    suspend fun getMovieBy(@Query("query") text: String, @Query("page") page: Int):Response<MovieResponse>

    @GET("search/tv")
    suspend fun getSeriesBy(@Query("query") text: String, @Query("page") page: Int):Response<SeriesResponse>






    @GET("search/person")
    suspend fun getPeopleBy(@Query("query") text: String, @Query("page") page: Int):Response<PeopleResponse>

    @GET("person/{id}")
    suspend fun getPersonDetail(@Path("id") personId: Int): Response<PeopleDetail>

    @GET("person/{id}/images")
    suspend fun getPersonImages(@Path("id") personId: Int): Response<ImageResponse>

    @GET("person/{id}/movie_credits")
    suspend fun getPersonsMovies(@Path("id") personId: Int):Response<MovieResponse>

    @GET("person/{id}/tv_credits")
    suspend fun getPersonSeries(@Path("id") personId: Int):Response<SeriesResponse>



}