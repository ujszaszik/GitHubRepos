package com.withintent.githubrepos.features.repos.details.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Web
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.withintent.compose.button.PrimaryButton
import com.withintent.compose.image.Avatar
import com.withintent.compose.layout.*
import com.withintent.compose.resources.Colors
import com.withintent.compose.resources.Dimens
import com.withintent.compose.resources.Fonts
import com.withintent.compose.resources.Images
import com.withintent.compose.scroll.enableVerticalScroll
import com.withintent.compose.separator.VerticalSeparator
import com.withintent.githubrepos.features.repos.details.model.RepoDetailsModel

@Composable
fun RepoDetailsScreen(
    isLoading: Boolean,
    repoDetails: RepoDetailsModel,
    onBrowseRequest: (String) -> Unit
) {

    LoadingBox(
        modifier = Modifier.fillMaxSize(),
        isLoading = isLoading,
    ) {

        TopCenterColumn(modifier = Modifier.enableVerticalScroll()) {

            LargeSpacer()

            Avatar(url = repoDetails.avatarUrl)

            DefaultSpacer()

            CenteredRow(modifier = Modifier.fillMaxWidth()) {

                Images.RepoImage()

                Text(
                    text = repoDetails.fullName,
                    textAlign = TextAlign.Center,
                    fontFamily = Fonts.enduranceFamily,
                    fontStyle = FontStyle.Italic,
                    color = Colors.blue,
                    fontSize = Dimens.repoTitleTextSize,
                )
            }

            DefaultSpacer()

            DoubleSpacer()

            Text(
                modifier = Modifier.padding(horizontal = Dimens.paddingDefault),
                text = repoDetails.description,
                textAlign = TextAlign.Start,
                fontFamily = Fonts.enduranceFamily,
                color = Colors.black,
                fontSize = Dimens.repoDescriptionTextSize,
            )

            DoubleSpacer()

            VerticalSeparator()

            FlowRow(
                mainAxisAlignment = FlowMainAxisAlignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = Dimens.paddingDefault,
                        horizontal = Dimens.paddingDouble
                    )
            ) {

                if (repoDetails.topics.isEmpty()) {
                    Text(RepoDetailsTexts.EMPTY_TOPICS)
                } else {
                    repoDetails.topics.forEach {
                        RepoDetailsTopicChip(it)
                    }
                }

            }

            DefaultSpacer()

            VerticalSeparator()

            DefaultSpacer()

            StarsCounter(repoDetails.stars)

            DefaultSpacer()

            WatchingCounter(repoDetails.watchers)

            DefaultSpacer()

            ForksCounter(repoDetails.forks)

            DefaultSpacer()

            VerticalSeparator()

            PrimaryButton(
                label = RepoDetailsTexts.REPO_BUTTON_LABEL,
                icon = Icons.Default.Web,
                onClick = { onBrowseRequest(repoDetails.url) })

            DoubleSpacer()

        } // TopCenterColumn

    } // LoadingBox

}