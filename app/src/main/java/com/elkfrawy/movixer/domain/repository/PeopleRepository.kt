package com.elkfrawy.movixer.domain.repository

import com.elkfrawy.movixer.domain.model.Image
import com.elkfrawy.movixer.domain.model.Movie
import com.elkfrawy.movixer.domain.model.PeopleDetail
import com.elkfrawy.movixer.domain.model.Series
import com.elkfrawy.movixer.domain.utils.Result

interface PeopleRepository {

    suspend fun getPersonDetail(personId: Int): Result<PeopleDetail>
    suspend fun getPersonImages(personId: Int): Result<List<Image>>
    suspend fun getPersonsMovies(personId: Int): Result<List<Movie>>
    suspend fun getPersonSeries(personId: Int): Result<List<Series>>

}