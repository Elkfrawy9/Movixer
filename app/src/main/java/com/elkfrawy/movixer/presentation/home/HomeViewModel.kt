package com.elkfrawy.movixer.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elkfrawy.movixer.domain.model.Movie
import com.elkfrawy.movixer.domain.usecase.movie.GetNowPlayingUseCase
import com.elkfrawy.movixer.domain.usecase.movie.GetPopularUseCase
import com.elkfrawy.movixer.domain.usecase.movie.GetTopRatedUseCase
import com.elkfrawy.movixer.domain.usecase.movie.GetUpComingUseCase
import com.elkfrawy.movixer.domain.utils.onFailure
import com.elkfrawy.movixer.domain.utils.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTopRated: GetTopRatedUseCase,
    private val getNowPlaying: GetNowPlayingUseCase,
    private val getPopular: GetPopularUseCase,
    private val getUpComing: GetUpComingUseCase
) : ViewModel() {


    private val _topRated: MutableLiveData<List<Movie>> = MutableLiveData()
    val topRatedViewModel: LiveData<List<Movie>> = _topRated

    private val _nowPlaying: MutableLiveData<List<Movie>> = MutableLiveData()
    val nowPlayingViewModel: LiveData<List<Movie>> = _nowPlaying

    private val _popular: MutableLiveData<List<Movie>> = MutableLiveData()
    val popularViewModel: LiveData<List<Movie>> = _popular

    private val _upComing: MutableLiveData<List<Movie>> = MutableLiveData()
    val upComingViewModel: LiveData<List<Movie>> = _upComing

    init {
        viewModelScope.launch {
            executeNowPlaying()
            executeUpComing()
            executePopular()
            executeTopRated()
        }

    }

    private suspend fun executeTopRated() {

        val result = getTopRated.execute(1)
        result.onSuccess {
            _topRated.value = it
        }.onFailure {
        }

    }

    private suspend fun executePopular() {

        val result = getPopular.execute(1)
        result.onSuccess {
            _popular.value = it
        }.onFailure {
        }

    }

    private suspend fun executeNowPlaying() {

        val result = getNowPlaying.execute(1)
        result.onSuccess {
            _nowPlaying.value = it
        }.onFailure {
        }

    }

    private suspend fun executeUpComing() {

        val result = getUpComing.execute(1)
        result.onSuccess {
            _upComing.value = it
        }.onFailure {
        }

    }


}