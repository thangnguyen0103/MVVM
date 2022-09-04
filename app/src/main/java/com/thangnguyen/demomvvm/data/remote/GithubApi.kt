package com.thangnguyen.demomvvm.data.remote

import com.thangnguyen.demomvvm.data.remote.model.RepoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    companion object {
        const val HTTPS_API_GITHUB_URL = "https://api.github.com/"
    }

    @GET("users/{user}/repos")
    suspend fun getRepoList(
        @Path("user") user: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 5
    ): List<RepoResponse>

    @GET("/repos/{user}/{repo_name}")
    suspend fun getRepoDetails(
        @Path("user") user: String?,
        @Path("repo_name") repoName: String?
    ): RepoResponse
}