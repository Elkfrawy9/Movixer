package com.elkfrawy.movixer.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.elkfrawy.movixer.domain.model.Cast
import com.elkfrawy.movixer.domain.model.Image
import com.elkfrawy.movixer.domain.model.Movie
import com.elkfrawy.movixer.domain.model.MovieDetails
import com.elkfrawy.movixer.domain.model.PeopleDetail
import com.elkfrawy.movixer.domain.model.Review
import com.elkfrawy.movixer.domain.model.Series
import com.elkfrawy.movixer.domain.model.SeriesDetails
import com.elkfrawy.movixer.domain.model.Video
import com.elkfrawy.movixer.domain.usecase.movie.GetMovieCastsUseCase
import com.elkfrawy.movixer.domain.usecase.movie.GetMovieDetailsUseCase
import com.elkfrawy.movixer.domain.usecase.movie.GetMovieRFlowUseCase
import com.elkfrawy.movixer.domain.usecase.movie.GetMovieRecoUseUse
import com.elkfrawy.movixer.domain.usecase.movie.GetMovieReviewsUseUse
import com.elkfrawy.movixer.domain.usecase.movie.GetMovieSimilarUseUse
import com.elkfrawy.movixer.domain.usecase.movie.GetMovieVideoUseCase
import com.elkfrawy.movixer.domain.usecase.person.GetPersonDetailUseCase
import com.elkfrawy.movixer.domain.usecase.person.GetPersonImagesUseCase
import com.elkfrawy.movixer.domain.usecase.person.GetPersonMoviesUseCase
import com.elkfrawy.movixer.domain.usecase.person.GetPersonSeriesUseCase
import com.elkfrawy.movixer.domain.usecase.series.GetSeriesCastsUseCase
import com.elkfrawy.movixer.domain.usecase.series.GetSeriesDetailsUseCase
import com.elkfrawy.movixer.domain.usecase.series.GetSeriesRFlowUseCase
import com.elkfrawy.movixer.domain.usecase.series.GetSeriesRecoUseCase
import com.elkfrawy.movixer.domain.usecase.series.GetSeriesReviewsUseCase
import com.elkfrawy.movixer.domain.usecase.series.GetSeriesVideosUseCase
import com.elkfrawy.movixer.domain.utils.onFailure
import com.elkfrawy.movixer.domain.utils.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor (
    private val getMovieDetails: GetMovieDetailsUseCase,
    private val getMovieReviews: GetMovieReviewsUseUse,
    private val getMovieReco: GetMovieRecoUseUse,
    private val getMovieVideo: GetMovieVideoUseCase,
    private val getMovieSimilar: GetMovieSimilarUseUse,
    private val getMovieCasts: GetMovieCastsUseCase,
    private val getSeriesReco: GetSeriesRecoUseCase,
    private val getSeriesCasts: GetSeriesCastsUseCase,
    private val getSeriesDetails: GetSeriesDetailsUseCase,
    private val getSeriesVideos: GetSeriesVideosUseCase,
    private val getSeriesReviews: GetSeriesReviewsUseCase,
    private val getPersonDetail: GetPersonDetailUseCase,
    private val getPersonImages: GetPersonImagesUseCase,
    private val getPersonMovies: GetPersonMoviesUseCase,
    private val getPersonSeries: GetPersonSeriesUseCase,
    private val getMovieR: GetMovieRFlowUseCase,
    private val getSeriesR: GetSeriesRFlowUseCase
): ViewModel(){


    private val _movieDetails: MutableLiveData<MovieDetails> = MutableLiveData()
    val movieDetailsLiveData: LiveData<MovieDetails> = _movieDetails

    private val _movieReviews: MutableLiveData<List<Review>> = MutableLiveData()
    val movieReviewsLiveData: LiveData<List<Review>> = _movieReviews

    private val _movieRecommendation: MutableLiveData<List<Movie>> = MutableLiveData()
    val movieRecommendationLiveData: LiveData<List<Movie>> = _movieRecommendation

    private val _movieSimilar: MutableLiveData<List<Movie>> = MutableLiveData()
    val movieSimilarLiveData: LiveData<List<Movie>> = _movieSimilar

    private val _movieCasts: MutableLiveData<List<Cast>> = MutableLiveData()
    val movieCastsLiveData: LiveData<List<Cast>> = _movieCasts

    private val _movieVideo: MutableLiveData<List<Video>> = MutableLiveData()
    val movieVideoLiveData: LiveData<List<Video>> = _movieVideo

    private val _seriesDetails: MutableLiveData<SeriesDetails> = MutableLiveData()
    val seriesDetailsLiveData: LiveData<SeriesDetails> = _seriesDetails

    private val _seriesReview: MutableLiveData<List<Review>> = MutableLiveData()
    val seriesReviewsLiveData: LiveData<List<Review>> = _seriesReview

    private val _seriesCasts: MutableLiveData<List<Cast>> = MutableLiveData()
    val seriesCastsLiveData: LiveData<List<Cast>> = _seriesCasts

    private val _seriesVideos: MutableLiveData<List<Video>> = MutableLiveData()
    val seriesVideosLiveData: LiveData<List<Video>> = _seriesVideos

    private val _seriesRecommendation: MutableLiveData<List<Series>> = MutableLiveData()
    val seriesRecommendationLiveData: LiveData<List<Series>> = _seriesRecommendation

    private val _personDetails: MutableLiveData<PeopleDetail> = MutableLiveData()
    val personDetailsLiveData: LiveData<PeopleDetail> = _personDetails

    private val _personImage: MutableLiveData<List<Image>> = MutableLiveData()
    val personImageLiveData: LiveData<List<Image>> = _personImage

    private val _personMovie: MutableLiveData<List<Movie>> = MutableLiveData()
    val personMovieLiveData: LiveData<List<Movie>> = _personMovie

    private val _personSeries: MutableLiveData<List<Series>> = MutableLiveData()
    val personSeriesLiveData: LiveData<List<Series>> = _personSeries


    suspend fun getMovieReview(movieId: Int): StateFlow<PagingData<Review>> {
        return getMovieR.execute(movieId).stateIn(viewModelScope)
    }

    suspend fun getSeriesReview(seriesId: Int): StateFlow<PagingData<Review>> {
        return getSeriesR.execute(seriesId).stateIn(viewModelScope)
    }



    fun getAllSeriesDetailsData(seriesId: Int){
        viewModelScope.launch {



            getSeriesCasts.execute(seriesId).onSuccess {
                _seriesCasts.value = it
            }.onFailure {

            }
            getSeriesVideos.execute(seriesId).onSuccess {
                _seriesVideos.value = it
            }.onFailure {

            }
            getSeriesDetails.execute(seriesId).onSuccess {
                _seriesDetails.value = it
            }.onFailure {

            }
            getSeriesReco.execute(seriesId, 1).onSuccess {
                _seriesRecommendation.value = it
            }.onFailure {

            }
            getSeriesReviews.execute(seriesId, 1).onSuccess {
                _seriesReview.value = it
            }.onFailure {

            }
        }
    }

    fun getAllPersonDetailsData(personId: Int){
        viewModelScope.launch {
            getPersonDetail.execute(personId).onSuccess {
                _personDetails.value = it
            }.onFailure {
            }
            getPersonImages.execute(personId).onSuccess {
                _personImage.value = it
            }.onFailure {

            }
            getPersonMovies.execute(personId).onSuccess {
                _personMovie.value = it
            }.onFailure {

            }
            getPersonSeries.execute(personId).onSuccess {
                _personSeries.value = it
            }.onFailure {

            }
        }
    }

    fun getAllMoviesDetailsData(movieId: Int){

        viewModelScope.launch {

            getMovieDetails.execute(movieId).onSuccess {
                _movieDetails.value = it
            }.onFailure {

            }
            getMovieReco.execute(movieId, 1).onSuccess {
                _movieRecommendation.value = it
            }.onFailure {

            }
            getMovieSimilar.execute(movieId, 1).onSuccess {
                _movieSimilar.value = it
            }.onFailure {

            }
            getMovieCasts.execute(movieId).onSuccess {
                _movieCasts.value = it
            }.onFailure {

            }
            getMovieReviews.execute(movieId, 1).onSuccess {
                _movieReviews.value = it
            }.onFailure {

            }
            getMovieVideo.execute(movieId).onSuccess {
                _movieVideo.value = it
            }.onFailure {

            }

        }

    }











}