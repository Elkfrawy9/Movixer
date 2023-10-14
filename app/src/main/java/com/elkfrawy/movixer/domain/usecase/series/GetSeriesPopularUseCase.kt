package com.elkfrawy.movixer.domain.usecase.series

import com.elkfrawy.movixer.domain.repository.MovieRepository
import com.elkfrawy.movixer.domain.repository.SeriesRepository
import javax.inject.Inject

class GetSeriesPopularUseCase @Inject constructor(val repo: SeriesRepository) {

    suspend fun execute(page: Int) = repo.getSeriesPopular(page)

}