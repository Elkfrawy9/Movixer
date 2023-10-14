package com.elkfrawy.movixer.data.remote.series

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.elkfrawy.movixer.data.MovieApi
import com.elkfrawy.movixer.data.model.SeriesResponse
import com.elkfrawy.movixer.domain.model.Series
import com.elkfrawy.movixer.presentation.utlis.AIRING_TODAY_LIST
import com.elkfrawy.movixer.presentation.utlis.ON_THE_AIR_LIST
import com.elkfrawy.movixer.presentation.utlis.SERIES_POPULAR_LIST
import com.elkfrawy.movixer.presentation.utlis.SERIES_TOP_RATED_LIST
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response


class SeriesPagingSource(val list: Int, val api: MovieApi) : PagingSource<Int, Series>() {

    override fun getRefreshKey(state: PagingState<Int, Series>): Int? {
        return state.anchorPosition?.let {
            val page = state.closestPageToPosition(it)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Series> {
        val page = params.key ?: 1
        return try {
            var response: Response<SeriesResponse>? = null

            when (list) {
                AIRING_TODAY_LIST -> response = api.getAiringToday(page)
                SERIES_POPULAR_LIST -> response = api.getSeriesPopular(page)
                ON_THE_AIR_LIST -> response = api.getOnTheAir(page)
                SERIES_TOP_RATED_LIST -> response = api.getSeriesTopRated(page)
            }
            val result = response?.body()

                LoadResult.Page(
                    result?.results ?: ArrayList<Series>(),
                    nextKey = if (result?.results!!.isEmpty()) null else response?.body()?.page?.plus(1),
                    prevKey = if (result.page == 1) null else result.page.minus(1)
                )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}