package com.elkfrawy.movixer.presentation.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elkfrawy.movixer.domain.model.Series
import com.elkfrawy.movixer.domain.usecase.series.GetAiringTodayUseCase
import com.elkfrawy.movixer.domain.usecase.series.GetOnTheAirUseCase
import com.elkfrawy.movixer.domain.usecase.series.GetSeriesPopularUseCase
import com.elkfrawy.movixer.domain.usecase.series.GetSeriesTopRatedUseCase
import com.elkfrawy.movixer.domain.utils.onFailure
import com.elkfrawy.movixer.domain.utils.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(
    private val getTopRated: GetSeriesTopRatedUseCase,
    private val getOnTheAir: GetOnTheAirUseCase,
    private val getPopular: GetSeriesPopularUseCase,
    private val getAiringToday: GetAiringTodayUseCase
) : ViewModel() {


    private val _topRated: MutableLiveData<List<Series>> = MutableLiveData()
    val topRatedViewModel: LiveData<List<Series>> = _topRated

    private val _onTheAir: MutableLiveData<List<Series>> = MutableLiveData()
    val onTheAirViewModel: LiveData<List<Series>> = _onTheAir

    private val _popular: MutableLiveData<List<Series>> = MutableLiveData()
    val popularViewModel: LiveData<List<Series>> = _popular

    private val _airingToday: MutableLiveData<List<Series>> = MutableLiveData()
    val airingTodayViewModel: LiveData<List<Series>> = _airingToday

    init {
        viewModelScope.launch {
            executeOnAirToday()
            executeAiringToday()
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

    private suspend fun executeOnAirToday() {

        val result = getOnTheAir.execute(1)
        result.onSuccess {
            _onTheAir.value = it
        }.onFailure {
        }

    }

    private suspend fun executeAiringToday() {

        val result = getAiringToday.execute(1)
        result.onSuccess {
            _airingToday.value = it
        }.onFailure {
        }

    }


}