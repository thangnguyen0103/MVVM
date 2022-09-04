package com.thangnguyen.demomvvm.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Repo(
    @PrimaryKey
    val id: Long,

    val name: String,

    val fullName: String,

    val user: User,

    val htmlUrl: String,

    val description: String,

    val url: String,

    val createdAt: Date,

    val updatedAt: Date,

    val pushedAt: Date,

    val gitUrl: String,

    val sshUrl: String,

    val cloneUrl: String,

    val svnUrl: String,

    val homepage: String?,

    val stargazersCount: Int,

    val watchers_count: Int,

    val language: String?,

    val hasIssues: Boolean,

    val hasDownloads: Boolean,

    val hasWiki: Boolean,

    val hasPages: Boolean,

    val forksCount: Int,

    val openIssuesCount: Int,

    val forks: Int,

    val openIssues: Int,


    val watchers: Int,

    val defaultBranch: String
)
