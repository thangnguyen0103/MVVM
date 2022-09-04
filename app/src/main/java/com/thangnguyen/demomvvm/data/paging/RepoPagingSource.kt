package com.thangnguyen.demomvvm.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.thangnguyen.demomvvm.data.Error
import com.thangnguyen.demomvvm.data.db.entity.Repo
import com.thangnguyen.demomvvm.data.remote.GithubApi


class RepoPagingSource(private val userName: String, private val githubApi: GithubApi) :
    PagingSource<Int, Repo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        val pageNumber = params.key ?: 1
        return try {
            val response = githubApi.getRepoList(user = userName, page = pageNumber).map {
                it.toEntity()
            }
            if (response.isEmpty()) {
                LoadResult.Error(Error.NoValue)
            } else {
                val nextPageNumber = pageNumber + 1
                LoadResult.Page(
                    data = response,
                    prevKey = null,
                    nextKey = nextPageNumber
                )
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        return state.anchorPosition
    }

}