package com.thangnguyen.demomvvm.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.thangnguyen.demomvvm.data.db.entity.Repo
import java.util.Date

@JsonClass(generateAdapter = true)
data class RepoResponse(
    val id: Long,
    val name: String,
    @Json(name = "full_name")
    val fullName: String,
    val owner: UserResponse,
    @Json(name = "html_url")
    val htmlUrl: String,
    val description: String?,
    val url: String,
    @Json(name = "created_at")
    val createdAt: Date,
    @Json(name = "updated_at")
    val updatedAt: Date,
    @Json(name = "pushed_at")
    val pushedAt: Date,
    @Json(name = "git_url")
    val gitUrl: String,
    @Json(name = "ssh_url")
    val sshUrl: String,
    @Json(name = "clone_url")
    val cloneUrl: String,
    @Json(name = "svn_url")
    val svnUrl: String,
    val homepage: String?,
    @Json(name = "stargazers_count")
    val stargazersCount: Int,
    @Json(name = "watchers_count")
    val watchers_count: Int,
    val language: String?,
    @Json(name = "has_issues")
    val hasIssues: Boolean,
    @Json(name = "has_downloads")
    val hasDownloads: Boolean,
    @Json(name = "has_wiki")
    val hasWiki: Boolean,
    @Json(name = "has_pages")
    val hasPages: Boolean,
    @Json(name = "forks_count")
    val forksCount: Int?,
    @Json(name = "open_issues_count")
    val openIssuesCount: Int?,
    val forks: Int?,
    @Json(name = "open_issues")
    val openIssues: Int?,
    val watchers: Int?,
    @Json(name = "default_branch")
    val defaultBranch: String
) {
    fun toEntity(): Repo {
        return Repo(
            id,
            name,
            fullName,
            user = owner.toEntity(),
            htmlUrl,
            description ?: "",
            url,
            createdAt,
            updatedAt,
            pushedAt,
            gitUrl,
            sshUrl,
            cloneUrl,
            svnUrl,
            homepage,
            stargazersCount,
            watchers_count,
            language,
            hasIssues,
            hasDownloads,
            hasWiki,
            hasPages,
            forksCount ?: 0,
            openIssuesCount ?: 0,
            forks ?: 0,
            openIssues ?: 0,
            watchers ?: 0,
            defaultBranch
        )
    }
}