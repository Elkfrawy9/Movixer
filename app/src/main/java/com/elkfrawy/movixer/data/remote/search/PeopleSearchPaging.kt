package com.elkfrawy.movixer.data.remote.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.elkfrawy.movixer.data.MovieApi
import com.elkfrawy.movixer.domain.model.People
import okio.IOException
import retrofit2.HttpException

class PeopleSearchPaging(val text: String, val api: MovieApi) : PagingSource<Int, People>() {

    override fun getRefreshKey(state: PagingState<Int, People>): Int? {
        return state.anchorPosition?.let {
            val page = state.closestPageToPosition(it)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, People> {
        return try {
            val page = params.key ?: 1
            val response = api.getPeopleBy(text, page).body()
            val data = response?.results ?: ArrayList<People>()

            LoadResult.Page(
                data,
                prevKey = if (response!!.page == 1) null else response.page - 1,
                nextKey = if (data.isEmpty()) null else response.page + 1
            )

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}