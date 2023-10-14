package com.elkfrawy.movixer.domain.usecase.series

import androidx.paging.PagingData
import com.elkfrawy.movixer.domain.model.Review
import com.elkfrawy.movixer.domain.repository.SeriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSeriesRFlowUseCase @Inject constructor(val repo: SeriesRepository){

    fun execute(seriesId: Int): Flow<PagingData<Review>> = repo.seriesReviewPagingData(seriesId)

}