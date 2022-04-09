package com.withintent.githubrepos.features.repos

import com.withintent.network.operation.networkFlow
import com.withintent.githubrepos.coroutines.ResourceFlow
import com.withintent.githubrepos.features.repos.details.model.RepoDetailsModel
import com.withintent.githubrepos.features.repos.list.model.ReposListModel
import javax.inject.Inject

class ReposRepository @Inject constructor(
    private val service: ReposService
) {

    fun searchByKeyword(keyword: String, pageNumber: Long): ResourceFlow<ReposListModel> =
        networkFlow { service.getSearchResultsForKeyword(keyword, pageNumber) }

    fun getDetails(owner: String, repo: String): ResourceFlow<RepoDetailsModel> =
        networkFlow { service.getRepositoryDetails(owner, repo) }

}