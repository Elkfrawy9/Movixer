package com.elkfrawy.movixer.data.remote.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.elkfrawy.movixer.data.MovieApi
import com.elkfrawy.movixer.domain.model.Movie
import retrofit2.HttpException

class MovieSearchPaging(val text: String, val api: MovieApi): PagingSource<Int, Movie>(){

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            val page = state.closestPageToPosition(it)
            page?.nextKey?.minus(1) ?: page?.prevKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            val response = api.getMovieBy(text, page).body()
            val data = response?.results ?: ArrayList<Movie>()

            LoadResult.Page(
                data,
                prevKey = if (response!!.page == 1) null else response.page - 1,
                nextKey = if (data.isEmpty()) null else response.page + 1
            )

        }catch (ex: HttpException){
            LoadResult.Error(ex)
        } catch (ex: HttpException) {
            LoadResult.Error(ex)
        }
    }
}