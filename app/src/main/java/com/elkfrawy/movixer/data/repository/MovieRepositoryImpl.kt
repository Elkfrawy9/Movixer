package com.elkfrawy.movixer.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.elkfrawy.movixer.data.MovieApi
import com.elkfrawy.movixer.data.remote.movie.MovieDataSource
import com.elkfrawy.movixer.data.remote.movie.MoviePagingSource
import com.elkfrawy.movixer.data.remote.movie.MovieReviewPagingSource
import com.elkfrawy.movixer.domain.model.Cast
import com.elkfrawy.movixer.domain.model.Movie
import com.elkfrawy.movixer.domain.model.MovieDetails
import com.elkfrawy.movixer.domain.model.Review
import com.elkfrawy.movixer.domain.model.Video
import com.elkfrawy.movixer.domain.repository.MovieRepository
import com.elkfrawy.movixer.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(val api: MovieApi, val movieSource: MovieDataSource) :
    MovieRepository {

    override suspend fun getNowPlaying(page: Int): Result<List<Movie>> =
        movieSource.getNowPlaying(page)

    override suspend fun getPopular(page: Int): Result<List<Movie>> = movieSource.getPopular(page)

    override suspend fun getTopRated(page: Int): Result<List<Movie>> = movieSource.getTopRated(page)

    override suspend fun getUpComing(page: Int): Result<List<Movie>> = movieSource.getUpComing(page)

    override suspend fun getMovieDetails(movieId: Int): Result<MovieDetails> =
        movieSource.getMovieDetails(movieId)

    override suspend fun getMovieRecommendation(movieId: Int, page: Int): Result<List<Movie>> =
        movieSource.getMovieRecommendation(movieId, page)

    override suspend fun getMovieSimilar(movieId: Int, page: Int): Result<List<Movie>> =
        movieSource.getMovieSimilar(movieId, page)

    override suspend fun getMovieReviews(movieId: Int, page: Int): Result<List<Review>> =
        movieSource.getMovieReviews(movieId, page)

    override suspend fun getMovieCasts(movieId: Int): Result<List<Cast>> =
        movieSource.getMovieCasts(movieId)

    override suspend fun getMovieVideo(movieId: Int): Result<List<Video>> =
        movieSource.getMovieVideo(movieId
        )

    override fun moviePagingData(list: Int): Flow<PagingData<Movie>> {
        return Pager(
            PagingConfig(
                pageSize = 20,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                prefetchDistance = 40
            ),
            pagingSourceFactory = { MoviePagingSource(list, api) }
        ).flow
    }

    override fun movieReviewPagingData(movieId: Int): Flow<PagingData<Review>> {
        return Pager<Int, Review>(
            PagingConfig(
                20,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                prefetchDistance = 40
            ),
            pagingSourceFactory = { MovieReviewPagingSource(api, movieId) }
        ).flow
    }
}