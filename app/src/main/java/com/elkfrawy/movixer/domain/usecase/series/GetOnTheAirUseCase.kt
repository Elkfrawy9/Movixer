package com.elkfrawy.movixer.domain.usecase.series

import com.elkfrawy.movixer.domain.repository.MovieRepository
import com.elkfrawy.movixer.domain.repository.SeriesRepository
import javax.inject.Inject

class GetOnTheAirUseCase @Inject constructor(val repo: SeriesRepository) {

    suspend fun execute(page: Int) = repo.getOnTheAir(page)

}