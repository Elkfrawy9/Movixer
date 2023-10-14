package com.elkfrawy.movixer.domain.repository

import androidx.paging.PagingData
import com.elkfrawy.movixer.domain.model.Movie
import com.elkfrawy.movixer.domain.model.People
import com.elkfrawy.movixer.domain.model.Series
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun movieSearch(text: String): Flow<PagingData<Movie>>
    fun seriesSearch(text: String): Flow<PagingData<Series>>
    fun peopleSearch(text: String): Flow<PagingData<People>>

}