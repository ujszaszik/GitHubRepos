package com.withintent.githubrepos.features.repos.details.mapper

import com.withintent.network.mapper.BaseMapper
import com.withintent.githubrepos.features.repos.details.model.RepoDetailsModel
import com.withintent.githubrepos.features.repos.details.network.RepoDetailsResponse

class RepoDetailsMapper : BaseMapper<RepoDetailsResponse, RepoDetailsModel> {

    override fun map(remote: RepoDetailsResponse): RepoDetailsModel {
        return RepoDetailsModel(
            fullName = remote.fullName,
            description = remote.description,
            url = remote.url,
            avatarUrl = remote.owner.avatarUrl,
            stars = remote.starsCount,
            watchers = remote.watchersCount,
            forks = remote.forksCount,
            topics = remote.topics
        )
    }
}