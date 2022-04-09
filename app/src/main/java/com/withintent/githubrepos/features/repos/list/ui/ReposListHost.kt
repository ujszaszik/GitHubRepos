package com.withintent.githubrepos.features.repos.list.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.withintent.compose.dialog.ErrorDialog
import com.withintent.compose.progress.ProgressBar
import com.withintent.compose.resources.Strings
import com.withintent.githubrepos.coroutines.collectAsStateValue
import com.withintent.githubrepos.features.repos.details.ui.RepoDetailsFetchParams
import com.withintent.githubrepos.features.repos.details.ui.RepoDetailsHost
import com.withintent.githubrepos.features.repos.list.model.owner
import com.withintent.githubrepos.features.repos.list.model.repoName
import com.withintent.githubrepos.navigation.graph.LocalNavController
import com.withintent.githubrepos.navigation.host.Host
import com.withintent.githubrepos.navigation.host.HostType
import com.withintent.githubrepos.navigation.host.acceptParam
import com.withintent.githubrepos.navigation.host.withData

const val REPOS_SEARCH_KEYWORD_KEY = "ReposList::SearchKeyword"

val ReposListHost = Host(
    route = "ReposList",
    title = "Results",
    type = HostType.SUB
).acceptParam(REPOS_SEARCH_KEYWORD_KEY)

@Composable
fun ReposListHost(keyword: String?, viewModel: ReposListViewModel = hiltViewModel()) {

    val navController = LocalNavController.current

    LaunchedEffect(key1 = keyword) {
        keyword?.let {
            viewModel.loadRepositories(it)
        } ?: navController.popBackStack()
    }

    val isLoading = viewModel.isLoading.collectAsStateValue() ?: false
    val isRefreshing = viewModel.isRefreshing.collectAsStateValue() ?: false
    val itemDetails = viewModel.itemDetails.collectAsStateValue()
    val errorMessage = viewModel.errorMessage.collectAsStateValue()

    errorMessage?.let { ErrorDialog(title = Strings.ERROR, message = it) }

    when {
        itemDetails != null -> {
            ReposListScreen(
                isLoading = isLoading,
                hasFinishedLoading = itemDetails.finishedLoading,
                keyword = keyword!!,
                itemsList = itemDetails.items,
                totalCount = itemDetails.totalCount,
                onItemClicked = {
                    val fetchParams = RepoDetailsFetchParams(it.owner, it.repoName)
                    navController.navigate(RepoDetailsHost.withData(fetchParams))
                },
                onLoadMore = { viewModel.loadRepositories(keyword) },
                isRefreshing = isRefreshing,
                onRefresh = { navController.navigate(ReposListHost.withData(keyword)) }
            )
        }
        else -> ProgressBar()
    }


}