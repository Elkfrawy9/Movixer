package com.elkfrawy.movixer.domain.usecase.movie

import androidx.paging.PagingData
import com.elkfrawy.movixer.domain.model.Review
import com.elkfrawy.movixer.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieRFlowUseCase@Inject constructor(val repo: MovieRepository){

    fun execute(movieId: Int): Flow<PagingData<Review>> = repo.movieReviewPagingData(movieId)

}