package com.elkfrawy.movixer.data.remote.movie

import android.annotation.SuppressLint
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.elkfrawy.movixer.data.MovieApi
import com.elkfrawy.movixer.data.model.MovieResponse
import com.elkfrawy.movixer.domain.model.Movie
import com.elkfrawy.movixer.presentation.utlis.NOW_PLAYING_LIST
import com.elkfrawy.movixer.presentation.utlis.POPULAR_LIST
import com.elkfrawy.movixer.presentation.utlis.TOP_RATED_LIST
import com.elkfrawy.movixer.presentation.utlis.UP_COMING_LIST
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response

class MoviePagingSource(val list: Int, val api: MovieApi) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            val page = state.closestPageToPosition(it)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        return try {
            var response: Response<MovieResponse>? = null

            when (list) {
                POPULAR_LIST -> response = api.getPopular(page)
                UP_COMING_LIST -> response = api.getUpComing(page)
                TOP_RATED_LIST -> response = api.getTopRated(page)
                NOW_PLAYING_LIST -> response = api.getNowPlaying(page)
            }
            val result = response?.body()

                LoadResult.Page(
                    result?.results ?: ArrayList<Movie>(),
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