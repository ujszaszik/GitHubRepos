package com.withintent.githubrepos.features.repos.search.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.withintent.githubrepos.coroutines.collectAsStateValue
import com.withintent.githubrepos.features.repos.list.ui.ReposListHost
import com.withintent.githubrepos.navigation.graph.LocalNavController
import com.withintent.githubrepos.navigation.host.BackPressStrategy
import com.withintent.githubrepos.navigation.host.Host
import com.withintent.githubrepos.navigation.host.HostType
import com.withintent.githubrepos.navigation.host.withData

val ReposSearchHost = Host(
    route = "ReposSearch",
    title = "Search",
    type = HostType.MAIN,
    backPressStrategy = BackPressStrategy.EXIT_APPLICATION
)

fun NavController.navigateToSearch() =
    navigate(ReposSearchHost.route) {
        popUpTo(ReposSearchHost.route) { inclusive = true }
    }


@Composable
fun ReposSearchHost(viewModel: ReposSearchViewModel = hiltViewModel()) {

    val searchInputError = viewModel.searchedTextInput.collectErrorState()

    val searchedKeyword = viewModel.searchedKeyword.collectAsStateValue()
    val navController = LocalNavController.current

    LaunchedEffect(key1 = searchedKeyword) {
        searchedKeyword?.let {
            viewModel.resetKeyword()
            navController.navigate(ReposListHost.withData(it))
        }
    }

    ReposSearchScreen(
        errorMessage = searchInputError,
        onTextChanged = { viewModel.onSearchTextChange(it) },
        onSearchRequested = { viewModel.onSearchRequest() }
    )
}