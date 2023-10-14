package com.elkfrawy.movixer.presentation.seeAll

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.elkfrawy.movixer.domain.model.Movie
import com.elkfrawy.movixer.domain.model.Series
import com.elkfrawy.movixer.domain.usecase.movie.GetMovieList
import com.elkfrawy.movixer.domain.usecase.series.GetSeriesPagingList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SeeAllViewModel @Inject constructor(
    private val movieList: GetMovieList,
    private val seriesList: GetSeriesPagingList
) : ViewModel() {

    suspend fun loadMovieList(list: Int): StateFlow<PagingData<Movie>> {
        return movieList.execute(list).stateIn(viewModelScope)
    }

    suspend fun loadSeriesList(list: Int): StateFlow<PagingData<Series>> {
        return seriesList.execute(list).stateIn(viewModelScope)
    }
}