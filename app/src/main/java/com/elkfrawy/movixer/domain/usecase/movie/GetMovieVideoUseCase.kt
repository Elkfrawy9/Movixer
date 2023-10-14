package com.elkfrawy.movixer.domain.usecase.movie

import com.elkfrawy.movixer.domain.model.MovieDetails
import com.elkfrawy.movixer.domain.model.Video
import com.elkfrawy.movixer.domain.repository.MovieRepository
import com.elkfrawy.movixer.domain.utils.Result
import javax.inject.Inject

class GetMovieVideoUseCase @Inject constructor(val repo: MovieRepository){

    suspend fun execute(movieId: Int): Result<List<Video>> = repo.getMovieVideo(movieId)

}