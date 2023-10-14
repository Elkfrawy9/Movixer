package com.elkfrawy.movixer.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.elkfrawy.movixer.data.MovieApi
import com.elkfrawy.movixer.data.remote.search.MovieSearchPaging
import com.elkfrawy.movixer.data.remote.search.PeopleSearchPaging
import com.elkfrawy.movixer.data.remote.search.SeriesSearchPaging
import com.elkfrawy.movixer.domain.model.Movie
import com.elkfrawy.movixer.domain.model.People
import com.elkfrawy.movixer.domain.model.Series
import com.elkfrawy.movixer.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(val api: MovieApi): SearchRepository {

    override fun movieSearch(text: String): Flow<PagingData<Movie>> {
        return Pager(
            PagingConfig(
                20,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                prefetchDistance = 40,
            ),
            pagingSourceFactory = {MovieSearchPaging(text, api)}
        ).flow
    }

    override fun seriesSearch(text: String): Flow<PagingData<Series>> {
        return Pager(
            PagingConfig(
                20,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                prefetchDistance = 40,
            ),
            pagingSourceFactory = {SeriesSearchPaging(text, api)}
        ).flow
    }

    override fun peopleSearch(text: String): Flow<PagingData<People>> {
        return Pager(
            PagingConfig(
                20,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                prefetchDistance = 40,
            ),
            pagingSourceFactory = {PeopleSearchPaging(text, api)}
        ).flow
    }
}