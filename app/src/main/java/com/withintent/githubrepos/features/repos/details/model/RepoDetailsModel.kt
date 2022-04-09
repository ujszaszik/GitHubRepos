package com.withintent.githubrepos.features.repos.details.model

import com.withintent.network.mapper.DataMappedFrom
import com.withintent.githubrepos.features.repos.details.network.RepoDetailsResponse

@DataMappedFrom(RepoDetailsResponse::class)
data class RepoDetailsModel(
    val fullName: String,
    val description: String,
    val url: String,
    val avatarUrl: String,
    val stars: Long,
    val watchers: Long,
    val forks: Long,
    val topics: List<String>
)