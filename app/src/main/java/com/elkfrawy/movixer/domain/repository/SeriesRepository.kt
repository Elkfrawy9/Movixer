package com.elkfrawy.movixer.domain.repository

import androidx.paging.PagingData
import com.elkfrawy.movixer.domain.model.Cast
import com.elkfrawy.movixer.domain.model.Movie
import com.elkfrawy.movixer.domain.model.Review
import com.elkfrawy.movixer.domain.model.Series
import com.elkfrawy.movixer.domain.model.SeriesDetails
import com.elkfrawy.movixer.domain.model.Video
import com.elkfrawy.movixer.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface SeriesRepository {

    suspend fun getAiringToday(page: Int): Result<List<Series>>
    suspend fun getOnTheAir(page: Int): Result<List<Series>>
    suspend fun getSeriesPopular(page: Int): Result<List<Series>>
    suspend fun getSeriesTopRated(page: Int): Result<List<Series>>
    fun seriesPagingData(list: Int): Flow<PagingData<Series>>
    fun seriesReviewPagingData(seriesId: Int): Flow<PagingData<Review>>

    suspend fun getSeriesDetails(seriesId: Int): Result<SeriesDetails>
    suspend fun getSeriesRecommendation(seriesId: Int, page: Int): Result<List<Series>>
    suspend fun getSeriesCasts(seriesId: Int): Result<List<Cast>>
    suspend fun getSeriesReviews(seriesId: Int, page: Int): Result<List<Review>>
    suspend fun getSeriesVideo(seriesId: Int): Result<List<Video>>

}