package com.withintent.githubrepos.features.repos

import com.withintent.network.call.Resource
import com.withintent.githubrepos.features.repos.details.model.RepoDetailsModel
import com.withintent.githubrepos.features.repos.list.model.ReposListModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReposService {

    @GET("search/repositories")
    suspend fun getSearchResultsForKeyword(
        @Query("q") keyword: String,
        @Query("page") pageNumber: Long
    ): Resource<ReposListModel>

    @GET("repos/{owner}/{repo}")
    suspend fun getRepositoryDetails(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Resource<RepoDetailsModel>
}