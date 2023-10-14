package com.elkfrawy.movixer.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.elkfrawy.movixer.data.MovieApi
import com.elkfrawy.movixer.data.remote.movie.MovieReviewPagingSource
import com.elkfrawy.movixer.data.remote.series.SeriesDataSource
import com.elkfrawy.movixer.data.remote.series.SeriesPagingSource
import com.elkfrawy.movixer.data.remote.series.SeriesReviewPagingSource
import com.elkfrawy.movixer.domain.model.Cast
import com.elkfrawy.movixer.domain.model.Review
import com.elkfrawy.movixer.domain.model.Series
import com.elkfrawy.movixer.domain.model.SeriesDetails
import com.elkfrawy.movixer.domain.model.Video
import com.elkfrawy.movixer.domain.repository.SeriesRepository
import com.elkfrawy.movixer.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SeriesRepositoryImpl @Inject constructor(
    val api: MovieApi,
    val seriesSource: SeriesDataSource
) :
    SeriesRepository {

    override suspend fun getAiringToday(page: Int): Result<List<Series>> =
        seriesSource.getAiringToday(page)

    override suspend fun getOnTheAir(page: Int): Result<List<Series>> =
        seriesSource.getOnTheAir(page)

    override suspend fun getSeriesPopular(page: Int): Result<List<Series>> =
        seriesSource.getSeriesPopular(page)

    override suspend fun getSeriesTopRated(page: Int): Result<List<Series>> =
        seriesSource.getSeriesTopRated(page)

    override suspend fun getSeriesDetails(seriesId: Int): Result<SeriesDetails> =
        seriesSource.getSeriesDetails(seriesId)

    override suspend fun getSeriesRecommendation(seriesId: Int, page: Int): Result<List<Series>> =
        seriesSource.getSeriesRecommendation(seriesId, page)

    override suspend fun getSeriesCasts(seriesId: Int): Result<List<Cast>> =
        seriesSource.getSeriesCasts(seriesId)

    override suspend fun getSeriesReviews(seriesId: Int, page: Int): Result<List<Review>> =
        seriesSource.getSeriesReviews(seriesId, page)

    override suspend fun getSeriesVideo(seriesId: Int): Result<List<Video>> =
        seriesSource.getSeriesVideo(seriesId)

    override fun seriesPagingData(list: Int): Flow<PagingData<Series>> {
        return Pager(
            PagingConfig(
                pageSize = 20,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                prefetchDistance = 40
            ),
            pagingSourceFactory = { SeriesPagingSource(list, api) }
        ).flow
    }

    override fun seriesReviewPagingData(seriesId: Int): Flow<PagingData<Review>> {
        return Pager<Int, Review>(
            PagingConfig(
                20,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                prefetchDistance = 40
            ),
            pagingSourceFactory = { SeriesReviewPagingSource(api, seriesId) }
        ).flow
    }
}