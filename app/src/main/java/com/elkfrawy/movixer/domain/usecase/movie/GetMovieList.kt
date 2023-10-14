package com.elkfrawy.movixer.domain.usecase.movie

import androidx.paging.PagingData
import com.elkfrawy.movixer.domain.model.Movie
import com.elkfrawy.movixer.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieList@Inject constructor(val repo: MovieRepository){

    fun execute(list: Int): Flow<PagingData<Movie>> = repo.moviePagingData(list)

}