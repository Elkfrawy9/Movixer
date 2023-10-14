package com.elkfrawy.movixer.data.remote.series

import com.elkfrawy.movixer.data.MovieApi
import com.elkfrawy.movixer.data.exception.DataNotAvailableException
import com.elkfrawy.movixer.data.exception.ServerException
import com.elkfrawy.movixer.domain.model.Cast
import com.elkfrawy.movixer.domain.model.Movie
import com.elkfrawy.movixer.domain.model.Review
import com.elkfrawy.movixer.domain.model.Series
import com.elkfrawy.movixer.domain.model.SeriesDetails
import com.elkfrawy.movixer.domain.model.Video
import com.elkfrawy.movixer.domain.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SeriesDataSourceImpl @Inject constructor(val api: MovieApi) : SeriesDataSource {

    override suspend fun getAiringToday(page: Int): Result<List<Series>> =
        withContext(Dispatchers.IO) {
            try {

                val response = api.getAiringToday(page)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.Success(it.results)
                    } ?: Result.Failure(DataNotAvailableException())
                } else Result.Failure(ServerException())
            }catch (ex: Exception){
                Result.Failure(ex)
            }
        }


    override suspend fun getOnTheAir(page: Int): Result<List<Series>> =
        withContext(Dispatchers.IO) {
            try {

                val response = api.getOnTheAir(page)
                println("Farrag: ${response.code()}")
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.Success(it.results)
                    } ?: Result.Failure(DataNotAvailableException())
                } else Result.Failure(ServerException())
            }catch (ex: Exception){
                Result.Failure(ex)
            }
        }

    override suspend fun getSeriesPopular(page: Int): Result<List<Series>> =
        withContext(Dispatchers.IO) {
            try {

                val response = api.getSeriesPopular(page)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.Success(it.results)
                    } ?: Result.Failure(DataNotAvailableException())
                } else Result.Failure(ServerException())
            }catch (ex: Exception){
                Result.Failure(ex)
            }
        }

    override suspend fun getSeriesTopRated(page: Int): Result<List<Series>> =
        withContext(Dispatchers.IO) {
            try {


                val response = api.getSeriesTopRated(page)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.Success(it.results)
                    } ?: Result.Failure(DataNotAvailableException())
                } else Result.Failure(ServerException())
            }catch (ex: Exception){
                Result.Failure(ex)
            }
        }

    override suspend fun getSeriesDetails(seriesId: Int): Result<SeriesDetails> =
        withContext(Dispatchers.IO) {
           try {

               val response = api.getSeriesDetails(seriesId)
               if (response.isSuccessful) {
                   response.body()?.let {
                       Result.Success(it)
                   } ?: Result.Failure(DataNotAvailableException())
               } else Result.Failure(ServerException())
           }catch (ex: Exception){
               Result.Failure(ex)
           }
        }

    override suspend fun getSeriesRecommendation(seriesId: Int, page: Int): Result<List<Series>> =
        withContext(Dispatchers.IO) {
            try {

                val response = api.getSeriesRecommendation(seriesId, page)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.Success(it.results)
                    } ?: Result.Failure(DataNotAvailableException())
                } else Result.Failure(ServerException())
            }catch (ex: Exception){
                Result.Failure(ex)
            }
        }

    override suspend fun getSeriesCasts(seriesId: Int): Result<List<Cast>> =
        withContext(Dispatchers.IO) {
            try {

                val response = api.getSeriesCasts(seriesId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.Success(it.cast)
                    } ?: Result.Failure(DataNotAvailableException())
                } else Result.Failure(ServerException())
            }catch (ex: Exception){
                Result.Failure(ex)
            }
        }

    override suspend fun getSeriesReviews(seriesId: Int, page: Int): Result<List<Review>> =
        withContext(Dispatchers.IO) {
            try {

                val response = api.getSeriesReviews(seriesId, page)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.Success(it.results)
                    } ?: Result.Failure(DataNotAvailableException())
                } else Result.Failure(ServerException())
            }catch (ex: Exception){
                Result.Failure(ex)
            }
        }

    override suspend fun getSeriesVideo(seriesId: Int): Result<List<Video>> =
        withContext(Dispatchers.IO) {
            try {

                val response = api.getSeriesVideos(seriesId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.Success(it.results)
                    } ?: Result.Failure(DataNotAvailableException())
                } else Result.Failure(ServerException())
            }catch (ex: Exception){
                Result.Failure(ex)
            }
        }
}