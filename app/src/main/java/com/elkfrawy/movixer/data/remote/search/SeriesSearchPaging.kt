package com.elkfrawy.movixer.data.remote.search

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

class SeriesSearchPaging(val text: String, val api: MovieApi) : PagingSource<Int, Series>()  {

    override fun getRefreshKey(state: PagingState<Int, Series>): Int? {
        return state.anchorPosition?.let {
            val page = state.closestPageToPosition(it)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Series> {
        return try {
            val page = params.key ?: 1
            val response = api.getSeriesBy(text, page).body()
            val data = response?.results ?: ArrayList<Series>()


            LoadResult.Page(
                data,
                nextKey = if (response?.results!!.isEmpty()) null else response.page.plus(1),
                prevKey = if (response.page == 1) null else response.page.minus(1)
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}