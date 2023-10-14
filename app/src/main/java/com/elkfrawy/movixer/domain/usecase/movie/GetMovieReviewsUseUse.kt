package com.elkfrawy.movixer.domain.usecase.movie

import com.elkfrawy.movixer.domain.model.Movie
import com.elkfrawy.movixer.domain.model.MovieDetails
import com.elkfrawy.movixer.domain.model.Review
import com.elkfrawy.movixer.domain.repository.MovieRepository
import com.elkfrawy.movixer.domain.utils.Result
import javax.inject.Inject

class GetMovieReviewsUseUse@Inject constructor(val repo: MovieRepository){

    suspend fun execute(movieId: Int, page: Int): Result<List<Review>> =
        repo.getMovieReviews(movieId, page)

}