package com.withintent.githubrepos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.ComposeNavigator
import com.withintent.githubrepos.navigation.host.Host
import com.withintent.githubrepos.navigation.host.compress
import com.withintent.githubrepos.navigation.host.extractHost

internal fun NavGraphBuilder.composable(
    host: Host,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) {
    addDestination(
        ComposeNavigator.Destination(provider[ComposeNavigator::class], content).apply {
            this.label = host.compress()
            this.route = host.route
            arguments.forEach { (argumentName, argument) ->
                addArgument(argumentName, argument)
            }
            deepLinks.forEach { deepLink ->
                addDeepLink(deepLink)
            }
        }
    )
}

fun NavDestination.asHost(): Host? {
    return label.toString().extractHost()
}