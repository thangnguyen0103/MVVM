package com.thangnguyen.demomvvm.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.thangnguyen.demomvvm.data.remote.GithubApi
import com.thangnguyen.demomvvm.data.Resource
import com.thangnguyen.demomvvm.data.db.entity.Repo
import com.thangnguyen.demomvvm.data.paging.RepoPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepoRepositoryImpl constructor(private val api: GithubApi) : RepoRepository {

    companion object {
        private const val PAGE_SIZE = 10
        private const val PREFETCH_DISTANCE = 2
    }

    override fun getRepoList(userName: String): Flow<PagingData<Repo>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = PREFETCH_DISTANCE),
            pagingSourceFactory = { RepoPagingSource(userName, api) }
        ).flow
    }

    override fun getRepoDetail(
        userId: String,
        projectId: String
    ): Flow<Resource<Repo>> = flow {
        emit(Resource.Loading)
        val resource = try {
            val response = api.getRepoDetails(userId, projectId)
            Resource.Success(response.toEntity())
        } catch (e: Throwable) {
            Resource.Fail(e)
        }
        emit(resource)
    }
}