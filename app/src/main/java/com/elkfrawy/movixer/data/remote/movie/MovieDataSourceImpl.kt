package com.elkfrawy.movixer.data.remote.movie

import com.elkfrawy.movixer.data.MovieApi
import com.elkfrawy.movixer.data.exception.ClientException
import com.elkfrawy.movixer.data.exception.DataNotAvailableException
import com.elkfrawy.movixer.data.exception.ServerException
import com.elkfrawy.movixer.domain.model.Cast
import com.elkfrawy.movixer.domain.model.Movie
import com.elkfrawy.movixer.domain.model.MovieDetails
import com.elkfrawy.movixer.domain.model.Review
import com.elkfrawy.movixer.domain.model.Video
import com.elkfrawy.movixer.domain.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(val api: MovieApi) : MovieDataSource {

    override suspend fun getNowPlaying(page: Int): Result<List<Movie>> =
        withContext(Dispatchers.IO) {
            try {
                val response = api.getNowPlaying(page)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.Success(it.results)
                    } ?: Result.Failure(DataNotAvailableException())
                } else if (response.code() in 400..499) Result.Failure(ClientException())
                else Result.Failure(ServerException())
            }catch (ex: Exception){
                Result.Failure(ex)
            }

        }


    override suspend fun getPopular(page: Int): Result<List<Movie>> =
        withContext(Dispatchers.IO) {

            try {
                val response = api.getPopular(page)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.Success(it.results)
                    } ?: Result.Failure(DataNotAvailableException())
                } else if (response.code() in 400..499) Result.Failure(ClientException())
                else Result.Failure(ServerException())
            }catch (ex: Exception){
                Result.Failure(ex)
            }

        }

    override suspend fun getTopRated(page: Int): Result<List<Movie>> =
        withContext(Dispatchers.IO) {
            try {
                val response = api.getTopRated(page)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.Success(it.results)
                    } ?: Result.Failure(DataNotAvailableException())
                } else if (response.code() in 400..499) Result.Failure(ClientException())
                else Result.Failure(ServerException())
            }catch (ex: Exception){
                Result.Failure(ex)
            }
        }

    override suspend fun getUpComing(page: Int): Result<List<Movie>> =
        withContext(Dispatchers.IO) {
            try {
                val response = api.getUpComing(page)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.Success(it.results)
                    } ?: Result.Failure(DataNotAvailableException())
                } else if (response.code() in 400..499) Result.Failure(ClientException())
                else Result.Failure(ServerException())
            }catch (ex: Exception){
                Result.Failure(ex)
            }
        }


    override suspend fun getMovieDetails(movieId: Int): Result<MovieDetails> =
        withContext(Dispatchers.IO) {
            try {

                val response = api.getMovieDetails(movieId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.Success(it)
                    } ?: Result.Failure(DataNotAvailableException())
                } else if (response.code() in 400..499) Result.Failure(ClientException())
                else Result.Failure(ServerException())
            }catch (ex: Exception){
                Result.Failure(ex)
            }
        }

    override suspend fun getMovieRecommendation(movieId: Int, page: Int): Result<List<Movie>> =
        withContext(Dispatchers.IO) {
            try {

                val response = api.getMovieRecommendation(movieId, page)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.Success(it.results)
                    } ?: Result.Failure(DataNotAvailableException())
                } else if (response.code() in 400..499) Result.Failure(ClientException())
                else Result.Failure(ServerException())
            }catch (ex: Exception){
                Result.Failure(ex)
            }
        }

    override suspend fun getMovieSimilar(movieId: Int, page: Int): Result<List<Movie>> =
        withContext(Dispatchers.IO) {
           try {

               val response = api.getMovieSimilar(movieId, page)
               if (response.isSuccessful) {
                   response.body()?.let {
                       Result.Success(it.results)
                   } ?: Result.Failure(DataNotAvailableException())
               } else if (response.code() in 400..499) Result.Failure(ClientException())
               else Result.Failure(ServerException())
           }catch (ex: Exception){
               Result.Failure(ex)
           }
        }

    override suspend fun getMovieReviews(movieId: Int, page: Int): Result<List<Review>> =

        withContext(Dispatchers.IO) {
            try {

                val response = api.getMovieReviews(movieId, page)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.Success(it.results)
                    } ?: Result.Failure(DataNotAvailableException())
                } else if (response.code() in 400..499) Result.Failure(ClientException())
                else Result.Failure(ServerException())
            }catch (ex: Exception){
                Result.Failure(ex)
            }
        }

    override suspend fun getMovieCasts(movieId: Int): Result<List<Cast>> = withContext(Dispatchers.IO) {
        try {
            val response = api.getMovieCasts(movieId)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.Success(it.cast)
                } ?: Result.Failure(DataNotAvailableException())
            } else if (response.code() in 400..499) Result.Failure(ClientException())
            else Result.Failure(ServerException())
        }catch (ex: Exception){
            Result.Failure(ex)
        }
    }

    override suspend fun getMovieVideo(movieId: Int): Result<List<Video>> = withContext(Dispatchers.IO) {
        try {
            val response = api.getMovieVideos(movieId)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.Success(it.results)
                } ?: Result.Failure(DataNotAvailableException())
            } else if (response.code() in 400..499) Result.Failure(ClientException())
            else Result.Failure(ServerException())
        }catch (ex: Exception){
            Result.Failure(ex)
        }
    }
}