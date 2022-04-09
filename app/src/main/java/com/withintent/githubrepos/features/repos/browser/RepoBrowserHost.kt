package com.withintent.githubrepos.features.repos.browser

import androidx.compose.runtime.Composable
import com.withintent.compose.web.WebScreen
import com.withintent.githubrepos.navigation.graph.LocalNavController
import com.withintent.githubrepos.navigation.host.Host
import com.withintent.githubrepos.navigation.host.HostType
import com.withintent.githubrepos.navigation.host.acceptParam

const val REPO_BROWSER_URL_KEY = "RepoBrowser::URL"

val RepoBrowserHost = Host(
    route = "RepoBrowserHost",
    title = "Browse",
    type = HostType.SUB
).acceptParam(REPO_BROWSER_URL_KEY)

@Composable
fun RepoBrowserHost(url: String?) {

    val navController = LocalNavController.current

    url?.let {
        WebScreen(url = it)
    } ?: navController.popBackStack()

}