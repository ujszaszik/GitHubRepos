package com.withintent.githubrepos.features.repos.list.network

import com.withintent.network.mapper.DataMapper
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.withintent.githubrepos.features.repos.list.mapper.ReposListMapper

@JsonClass(generateAdapter = true)
@DataMapper(ReposListMapper::class)
data class ReposListResponse(
    @Json(name = "total_count") val count: Long,
    val items: List<ReposListItem>
)


@JsonClass(generateAdapter = true)
data class ReposListItem(
    val id: Int,
    val name: String,
    val description: String?,
    @Json(name = "full_name") val fullName: String
)