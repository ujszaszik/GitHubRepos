package com.withintent.githubrepos.features.main.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.withintent.githubrepos.features.repos.search.ui.navigateToSearch
import com.withintent.githubrepos.navigation.graph.LocalNavController
import com.withintent.githubrepos.navigation.host.Host

val SplashHost = Host(route = "SplashHost")

@Composable
fun SplashHost(viewModel: SplashViewModel = hiltViewModel()) {

    val isSplashFinished = viewModel.isSplashFinished.observeAsState().value ?: false

    if (isSplashFinished) {
        LocalNavController.current.navigateToSearch()
    }

    SplashScreen()
}