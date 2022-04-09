package com.withintent.githubrepos.features.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.imePadding
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.withintent.compose.keyboard.keyboardAsState
import com.withintent.compose.resources.Colors
import com.withintent.compose.resources.Dimens
import com.withintent.compose.resources.Icons
import com.withintent.extension.empty
import com.withintent.githubrepos.coroutines.collectAsStateValue
import com.withintent.githubrepos.features.main.util.ActionBarChannel
import com.withintent.githubrepos.navigation.graph.ApplicationGraph
import com.withintent.githubrepos.navigation.graph.LocalNavController
import com.withintent.githubrepos.navigation.host.*

@Composable
fun MainHost(viewModel: MainViewModel) {

    val navController = LocalNavController.current

    var host by remember { mutableStateOf<Host?>(null) }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        host = destination.label.toString().extractHost()
    }

    val onBackPressed = viewModel.onBackPressed.collectAsStateValue()

    LaunchedEffect(onBackPressed) {
        if (true == onBackPressed) {
            when (host?.backPressStrategy) {
                BackPressStrategy.POP_BACKSTACK -> navController.popBackStack()
                BackPressStrategy.EXIT_APPLICATION -> viewModel.onExitRequest()
                else -> Unit
            }
            viewModel.resetBackPress()
        }
    }

    val isKeyboardOpened = keyboardAsState().value.isOpened()

    var scaffoldModifier = Modifier.statusBarsPadding()
    if (!isKeyboardOpened) scaffoldModifier = scaffoldModifier.navigationBarsWithImePadding()

    ProvideWindowInsets {
        Scaffold(
            modifier = scaffoldModifier,
            topBar = {
                if (host.showTopAppBar())
                    TopAppBar(
                        title = {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = host?.title ?: String.empty,
                                color = Colors.white,
                                textAlign = TextAlign.Center,
                                fontSize = Dimens.appBarTextSize
                            )
                        }, // Title
                        navigationIcon = {
                            if (host.isSubHost()) {
                                IconButton(onClick = { navController.navigateUp() }) {
                                    Icons.BackArrowIcon()
                                }
                            }
                        }, // NavigationIcon
                        actions = {
                            if (true == host?.type?.showSearchIcon) {
                                IconButton(onClick = { ActionBarChannel.navigateToSearchPage() }) {
                                    Icons.SearchIcon()
                                }
                            }
                            if (true == host?.type?.showExitIcon) {
                                IconButton(onClick = { ActionBarChannel.closeApplication() }) {
                                    Icons.ExitIcon()
                                }
                            }
                        }, // Actions
                        backgroundColor = Colors.black
                    )
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .imePadding()
                ) {
                    ApplicationGraph()
                }
            },
        )
    }
}