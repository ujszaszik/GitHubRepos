package com.withintent.githubrepos.features.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.withintent.githubrepos.coroutines.collectAsStateValue
import com.withintent.githubrepos.features.main.util.ActionBarChannel
import com.withintent.githubrepos.features.main.util.ActionBarEvent
import com.withintent.githubrepos.features.main.util.KeyboardManager
import com.withintent.githubrepos.features.main.util.LocalKeyboardManager
import com.withintent.githubrepos.features.repos.search.ui.navigateToSearch
import com.withintent.githubrepos.navigation.graph.LocalNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberNavController()
            val keyboardManager = KeyboardManager(this)

            val exitRequest = viewModel.onExitRequest.collectAsStateValue()
            LaunchedEffect(exitRequest) {
                if (true == exitRequest) finishAffinity().run { viewModel.resetExitRequest() }
            }

            val actionBarEvent = ActionBarChannel.receive().collectAsStateValue()
            when (actionBarEvent) {
                is ActionBarEvent.NavigateToSearchPage -> navController.navigateToSearch()
                is ActionBarEvent.CloseApplication -> finishAffinity()
                else -> Unit
            }
            actionBarEvent?.let { ActionBarChannel.reset() }

            CompositionLocalProvider(
                LocalNavController provides navController,
                LocalKeyboardManager provides keyboardManager
            ) { MainHost(viewModel) }
        }
    }

    override fun onBackPressed() {
        viewModel.onBackPressed()
    }

}