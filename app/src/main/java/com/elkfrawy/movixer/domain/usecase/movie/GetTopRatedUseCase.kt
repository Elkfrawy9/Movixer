package com.elkfrawy.movixer.domain.usecase.movie

import com.elkfrawy.movixer.domain.repository.MovieRepository
import javax.inject.Inject

class GetTopRatedUseCase @Inject constructor(val repo: MovieRepository) {

    suspend fun execute(page: Int) = repo.getTopRated(page)
}