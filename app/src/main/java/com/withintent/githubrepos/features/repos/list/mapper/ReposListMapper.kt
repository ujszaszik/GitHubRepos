package com.withintent.githubrepos.features.repos.list.mapper

import com.withintent.extension.empty
import com.withintent.network.mapper.BaseMapper
import com.withintent.githubrepos.features.repos.list.model.RepoItem
import com.withintent.githubrepos.features.repos.list.model.ReposListModel
import com.withintent.githubrepos.features.repos.list.network.ReposListItem
import com.withintent.githubrepos.features.repos.list.network.ReposListResponse

class ReposListMapper : BaseMapper<ReposListResponse, ReposListModel> {

    override fun map(remote: ReposListResponse): ReposListModel {
        val items = remote.items.map { RepoItemMapper.map(it) }
        return ReposListModel(remote.count, items)
    }
}

object RepoItemMapper : BaseMapper<ReposListItem, RepoItem> {

    override fun map(remote: ReposListItem): RepoItem {
        return RepoItem(
            fullName = remote.fullName,
            description = remote.description ?: String.empty
        )
    }
}