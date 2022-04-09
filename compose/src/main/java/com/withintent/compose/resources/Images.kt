package com.withintent.compose.resources

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.withintent.compose.R
import com.withintent.extension.empty

object Images {

    @Composable
    fun GitHubLogo() {
        Image(
            painter = painterResource(R.drawable.github_logo),
            modifier = Modifier
                .width(Dimens.gitHubLogoWidth)
                .height(Dimens.gitHubLogoHeight),
            contentDescription = String.empty
        )
    }

    @Composable
    fun FromIntentLogo() {
        Image(
            painter = painterResource(R.drawable.from_intent),
            modifier = Modifier
                .width(Dimens.intentLogoWidth)
                .height(Dimens.intentLogoHeight),
            contentDescription = String.empty
        )
    }

    @Composable
    fun RepoImage() {
        Image(
            painter = painterResource(R.drawable.icon_repo),
            modifier = Modifier
                .width(Dimens.largerIconSize)
                .height(Dimens.largerIconSize),
            contentDescription = String.empty
        )
    }

    @Composable
    fun StarsImage() {
        Image(
            painter = painterResource(R.drawable.icon_stars),
            modifier = Modifier
                .width(Dimens.largerIconSize)
                .height(Dimens.largerIconSize),
            contentDescription = String.empty
        )
    }

    @Composable
    fun WatchersImage() {
        Image(
            painter = painterResource(R.drawable.icon_watching),
            modifier = Modifier
                .width(Dimens.largerIconSize)
                .height(Dimens.largerIconSize),
            contentDescription = String.empty
        )
    }

    @Composable
    fun ForksImage() {
        Image(
            painter = painterResource(R.drawable.icon_forks),
            modifier = Modifier
                .width(Dimens.largerIconSize)
                .height(Dimens.largerIconSize),
            contentDescription = String.empty
        )
    }
}