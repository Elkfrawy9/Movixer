package com.elkfrawy.movixer.data.repository

import com.elkfrawy.movixer.data.remote.people.PeopleRemoteSource
import com.elkfrawy.movixer.domain.model.Image
import com.elkfrawy.movixer.domain.model.Movie
import com.elkfrawy.movixer.domain.model.PeopleDetail
import com.elkfrawy.movixer.domain.model.Series
import com.elkfrawy.movixer.domain.repository.PeopleRepository
import com.elkfrawy.movixer.domain.utils.Result
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(val source: PeopleRemoteSource) : PeopleRepository {

    override suspend fun getPersonDetail(personId: Int): Result<PeopleDetail> =
        source.getPersonDetail(personId)

    override suspend fun getPersonImages(personId: Int): Result<List<Image>> =
        source.getPersonImages(personId)

    override suspend fun getPersonsMovies(personId: Int): Result<List<Movie>> =
        source.getPersonsMovies(personId)

    override suspend fun getPersonSeries(personId: Int): Result<List<Series>> =
        source.getPersonSeries(personId)

}