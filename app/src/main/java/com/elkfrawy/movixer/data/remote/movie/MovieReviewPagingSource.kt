package com.elkfrawy.movixer.data.remote.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.elkfrawy.movixer.data.MovieApi
import com.elkfrawy.movixer.domain.model.Review
import retrofit2.HttpException
import java.io.IOException

class MovieReviewPagingSource(val api: MovieApi, val movieId: Int): PagingSource<Int, Review>(){

    override fun getRefreshKey(state: PagingState<Int, Review>): Int? {
        return state.anchorPosition?.let {
            val page = state.closestPageToPosition(it)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {
        return try {
            val page = params.key ?: 1
            val response = api.getMovieReviews(movieId, page)

            LoadResult.Page(
                response.body()?.results ?: ArrayList<Review>(),
                prevKey = if (response.body()?.page == 1) null else response.body()?.page?.minus(1),
                nextKey = if (response.body()?.results!!.isEmpty()) null else response.body()?.page?.plus(1)
            )
        }catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}