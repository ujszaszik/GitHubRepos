package com.withintent.githubrepos.features.repos.list.model

import com.withintent.extension.second
import com.withintent.network.mapper.DataMappedFrom
import com.withintent.githubrepos.features.repos.list.network.ReposListResponse

@DataMappedFrom(ReposListResponse::class)
class ReposListModel(
    val itemsCount: Long,
    val items: List<RepoItem>
)

data class RepoItem(
    val fullName: String,
    val description: String
)

val RepoItem.owner: String
    get() = fullName.split("/").first()

val RepoItem.repoName: String
    get() = fullName.split("/").second()