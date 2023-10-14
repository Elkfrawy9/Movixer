package com.elkfrawy.movixer.data.remote.people

import com.elkfrawy.movixer.data.MovieApi
import com.elkfrawy.movixer.data.exception.DataNotAvailableException
import com.elkfrawy.movixer.data.exception.ServerException
import com.elkfrawy.movixer.domain.model.Image
import com.elkfrawy.movixer.domain.model.Movie
import com.elkfrawy.movixer.domain.model.PeopleDetail
import com.elkfrawy.movixer.domain.model.Series
import com.elkfrawy.movixer.domain.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PeopleRemoteSourceImpl @Inject constructor(val api: MovieApi) : PeopleRemoteSource {

    override suspend fun getPersonDetail(personId: Int): Result<PeopleDetail> =
        withContext(Dispatchers.IO) {
            try {

                val response = api.getPersonDetail(personId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.Success(it)
                    } ?: Result.Failure(DataNotAvailableException())
                } else Result.Failure(ServerException())
            } catch (ex: Exception) {
                Result.Failure(ex)
            }
        }

    override suspend fun getPersonImages(personId: Int): Result<List<Image>> =
        withContext(Dispatchers.IO) {
            try {

                val response = api.getPersonImages(personId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.Success(it.profiles)
                    } ?: Result.Failure(DataNotAvailableException())
                } else Result.Failure(ServerException())
            } catch (ex: Exception) {
                Result.Failure(ex)
            }
        }

    override suspend fun getPersonsMovies(personId: Int): Result<List<Movie>> =
        withContext(Dispatchers.IO) {
            try {

                val response = api.getPersonsMovies(personId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.Success(it.cast)
                    } ?: Result.Failure(DataNotAvailableException())
                } else Result.Failure(ServerException())
            } catch (ex: Exception) {
                Result.Failure(ex)
            }
        }

    override suspend fun getPersonSeries(personId: Int): Result<List<Series>> =
        withContext(Dispatchers.IO) {
            try {

                val response = api.getPersonSeries(personId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.Success(it.cast)
                    } ?: Result.Failure(DataNotAvailableException())
                } else Result.Failure(ServerException())
            } catch (ex: Exception) {
                Result.Failure(ex)
            }
        }
}