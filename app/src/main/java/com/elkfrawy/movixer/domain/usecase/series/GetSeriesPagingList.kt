package com.elkfrawy.movixer.domain.usecase.series

import androidx.paging.PagingData
import com.elkfrawy.movixer.domain.model.Movie
import com.elkfrawy.movixer.domain.model.Series
import com.elkfrawy.movixer.domain.repository.MovieRepository
import com.elkfrawy.movixer.domain.repository.SeriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSeriesPagingList@Inject constructor(val repo: SeriesRepository){

    fun execute(list: Int): Flow<PagingData<Series>> = repo.seriesPagingData(list)

}