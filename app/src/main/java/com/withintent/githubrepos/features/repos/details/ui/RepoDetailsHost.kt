package com.withintent.githubrepos.features.repos.details.ui

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.withintent.compose.dialog.ErrorDialog
import com.withintent.compose.resources.Strings
import com.withintent.githubrepos.coroutines.collectAsStateValue
import com.withintent.githubrepos.features.repos.browser.RepoBrowserHost
import com.withintent.githubrepos.navigation.graph.LocalNavController
import com.withintent.githubrepos.navigation.host.Host
import com.withintent.githubrepos.navigation.host.HostType
import com.withintent.githubrepos.navigation.host.acceptParam
import com.withintent.githubrepos.navigation.host.withEncodedData

const val REPO_DETAILS_FETCH_PARAMS_KEY = "RepoDetails::FetchParams"

val RepoDetailsHost = Host(
    route = "RepoDetailsHost",
    title = "Details",
    type = HostType.SUB
).acceptParam(REPO_DETAILS_FETCH_PARAMS_KEY)

@Composable
fun RepoDetailsHost(
    fetchParams: RepoDetailsFetchParams?,
    viewModel: RepoDetailsViewModel = hiltViewModel()
) {

    val navController = LocalNavController.current

    var isAlreadyLoaded by remember { mutableStateOf(false) }

    fetchParams?.let {
        if (!isAlreadyLoaded) {
            viewModel.loadDetails(fetchParams.owner, fetchParams.repo)
            isAlreadyLoaded = true
        }
    } ?: navController.popBackStack()

    val isLoading = viewModel.isLoading.collectAsStateValue() ?: false
    val details = viewModel.details.collectAsStateValue()
    val errorMessage = viewModel.errorMessage.collectAsStateValue()

    details?.let {
        RepoDetailsScreen(
            isLoading = isLoading,
            repoDetails = it,
            onBrowseRequest = { url ->
                navController.navigate(RepoBrowserHost.withEncodedData(url))
            }
        )
    }

    errorMessage?.let {
        ErrorDialog(
            title = Strings.ERROR,
            message = it,
            onClosed = { navController.popBackStack() }
        )
    }

}