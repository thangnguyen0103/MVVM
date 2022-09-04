package com.thangnguyen.demomvvm.data.repository

import androidx.paging.PagingData
import com.thangnguyen.demomvvm.data.remote.model.RepoResponse
import com.thangnguyen.demomvvm.data.Resource
import com.thangnguyen.demomvvm.data.db.entity.Repo
import kotlinx.coroutines.flow.Flow

interface RepoRepository {
    fun getRepoList(userName: String): Flow<PagingData<Repo>>
    fun getRepoDetail(userId: String, projectId: String): Flow<Resource<Repo>>
}