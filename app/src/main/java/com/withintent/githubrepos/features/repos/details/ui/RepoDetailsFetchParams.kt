package com.withintent.githubrepos.features.repos.details.ui

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepoDetailsFetchParams(
    val owner: String,
    val repo: String
)