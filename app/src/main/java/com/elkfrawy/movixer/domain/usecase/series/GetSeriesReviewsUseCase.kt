package com.elkfrawy.movixer.domain.usecase.series

import com.elkfrawy.movixer.domain.repository.SeriesRepository
import javax.inject.Inject

class GetSeriesReviewsUseCase @Inject constructor(val repo: SeriesRepository) {

    suspend fun execute(seriesId: Int, page: Int) = repo.getSeriesReviews(seriesId, page)

}