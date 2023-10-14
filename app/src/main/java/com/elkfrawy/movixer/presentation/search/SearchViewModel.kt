package com.elkfrawy.movixer.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.elkfrawy.movixer.domain.model.Movie
import com.elkfrawy.movixer.domain.model.People
import com.elkfrawy.movixer.domain.model.Series
import com.elkfrawy.movixer.domain.usecase.search.GetSearchedMovieUseCase
import com.elkfrawy.movixer.domain.usecase.search.GetSearchedPeopleUseCase
import com.elkfrawy.movixer.domain.usecase.search.GetSearchedSeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchedMovie: GetSearchedMovieUseCase,
    private val getSearchedPeople: GetSearchedPeopleUseCase,
    private val getSearchedSeries: GetSearchedSeriesUseCase,
): ViewModel() {

    val textStateFlow: MutableStateFlow<String> = MutableStateFlow("")

    suspend fun loadMovieList(text: String): StateFlow<PagingData<Movie>> {
        return getSearchedMovie.execute(text).stateIn(viewModelScope)
    }

    suspend fun loadSeriesList(text: String): StateFlow<PagingData<Series>> {
        return getSearchedSeries.execute(text).stateIn(viewModelScope)
    }

    suspend fun loadPeopleList(text: String): StateFlow<PagingData<People>> {
        return getSearchedPeople.execute(text).stateIn(viewModelScope)
    }

}