package com.withintent.githubrepos.features.repos.details.network

import com.withintent.network.mapper.DataMapper
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.withintent.githubrepos.features.repos.details.mapper.RepoDetailsMapper

@JsonClass(generateAdapter = true)
@DataMapper(RepoDetailsMapper::class)
data class RepoDetailsResponse(
    val id: Long,
    val description: String,
    val owner: RepoDetailsOwner,
    @Json(name = "full_name") val fullName: String,
    @Json(name = "html_url") val url: String,
    @Json(name = "stargazers_count") val starsCount: Long,
    @Json(name = "subscribers_count") val watchersCount: Long,
    @Json(name = "forks_count") val forksCount: Long,
    @Json(name = "topics") val topics: List<String>
)

@JsonClass(generateAdapter = true)
data class RepoDetailsOwner(
    @Json(name = "avatar_url") val avatarUrl: String,
)