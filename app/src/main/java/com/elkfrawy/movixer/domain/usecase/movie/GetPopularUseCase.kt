package com.elkfrawy.movixer.domain.usecase.movie

import com.elkfrawy.movixer.domain.repository.MovieRepository
import javax.inject.Inject

class GetPopularUseCase @Inject constructor(val repo: MovieRepository) {

    suspend fun execute(page: Int) = repo.getPopular(page)

}