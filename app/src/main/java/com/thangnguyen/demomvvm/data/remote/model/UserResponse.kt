package com.thangnguyen.demomvvm.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.thangnguyen.demomvvm.data.db.entity.User

@JsonClass(generateAdapter = true)
data class UserResponse(
    val id: Long,

    val login: String,

    @Json(name = "avatar_url")
    val avatarUrl: String,
    @Json(name = "gravatar_id")
    val gravatarId: String,
    val url: String,
    @Json(name = "html_url")
    val htmlUrl: String,
    @Json(name = "followers_url")
    val followersUrl: String,
    @Json(name = "following_url")
    val followingUrl: String,
    @Json(name = "gists_url")
    val gistsUrl: String,
    @Json(name = "starred_url")
    val starredUrl: String,
    @Json(name = "subscriptions_url")
    val subscriptionsUrl: String,
    @Json(name = "organizations_url")
    val organizationsUrl: String,
    @Json(name = "repos_url")
    val reposUrl: String,
    @Json(name = "events_url")
    val eventsUrl: String,
    @Json(name = "received_events_url")
    val receivedEventsUrl: String,
    val type: String
) {
    fun toEntity(): User {
        return User(
            id,
            login,
            avatarUrl,
            gravatarId,
            url,
            htmlUrl,
            followersUrl,
            followingUrl,
            gistsUrl,
            starredUrl,
            subscriptionsUrl,
            organizationsUrl,
            reposUrl,
            eventsUrl,
            receivedEventsUrl,
            type
        )
    }
}