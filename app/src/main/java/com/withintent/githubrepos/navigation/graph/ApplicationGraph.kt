package com.withintent.githubrepos.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.withintent.githubrepos.features.main.splash.SplashHost
import com.withintent.githubrepos.features.repos.browser.REPO_BROWSER_URL_KEY
import com.withintent.githubrepos.features.repos.browser.RepoBrowserHost
import com.withintent.githubrepos.features.repos.details.ui.REPO_DETAILS_FETCH_PARAMS_KEY
import com.withintent.githubrepos.features.repos.details.ui.RepoDetailsFetchParams
import com.withintent.githubrepos.features.repos.details.ui.RepoDetailsHost
import com.withintent.githubrepos.features.repos.list.ui.REPOS_SEARCH_KEYWORD_KEY
import com.withintent.githubrepos.features.repos.list.ui.ReposListHost
import com.withintent.githubrepos.features.repos.search.ui.ReposSearchHost
import com.withintent.githubrepos.navigation.arguments.retainEncodedParam
import com.withintent.githubrepos.navigation.arguments.retainParam
import com.withintent.githubrepos.navigation.composable

val LocalNavController =
    compositionLocalOf<NavHostController> { error("LocalComposition NavController not present") }

@Composable
fun ApplicationGraph() {

    val navController = LocalNavController.current

    NavHost(navController = navController, startDestination = SplashHost.route) {

        composable(SplashHost) {
            SplashHost()
        }

        composable(ReposSearchHost) {
            ReposSearchHost()
        }

        composable(ReposListHost) {
            val keyword = it.retainParam<String>(REPOS_SEARCH_KEYWORD_KEY)
            ReposListHost(keyword)
        }

        composable(RepoDetailsHost) {
            val fetchParams = it.retainParam<RepoDetailsFetchParams>(REPO_DETAILS_FETCH_PARAMS_KEY)
            RepoDetailsHost(fetchParams)
        }

        composable(RepoBrowserHost) {
            val url = it.retainEncodedParam<String>(REPO_BROWSER_URL_KEY)
            RepoBrowserHost(url)
        }
    }

}