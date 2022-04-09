package com.withintent.githubrepos.features.repos.list.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import com.withintent.compose.layout.DoubleSpacer
import com.withintent.compose.resources.Colors
import com.withintent.compose.resources.Dimens
import com.withintent.compose.resources.Fonts
import com.withintent.compose.resources.Images
import com.withintent.githubrepos.features.repos.list.model.RepoItem
import com.withintent.githubrepos.formatter.TextAnnotator

@Composable
fun ReposListItemScreen(
    modifier: Modifier = Modifier,
    repoItem: RepoItem,
    keyword: String,
    onItemClick: (RepoItem) -> Unit
) {

    Card(
        modifier = modifier.padding(Dimens.paddingHalf),
        shape = RoundedCornerShape(Dimens.cardCornerRadius),
        elevation = Dimens.cardElevation
    ) {

        Column(modifier = Modifier
            .clip(RoundedCornerShape(Dimens.cardCornerRadius))
            .clickable { onItemClick.invoke(repoItem) }
            .padding(horizontal = Dimens.paddingDefault)
        ) {

            DoubleSpacer()

            Row(modifier = Modifier.fillMaxWidth()) {

                Images.RepoImage()

                Text(
                    text = TextAnnotator.getHighlightedText(repoItem.fullName, keyword),
                    textAlign = TextAlign.Start,
                    fontFamily = Fonts.enduranceFamily,
                    fontStyle = FontStyle.Italic,
                    color = Colors.blue,
                    fontSize = Dimens.repoTitleTextSize,
                )
            }

            DoubleSpacer()

            Text(
                text = TextAnnotator.getHighlightedText(repoItem.description, keyword),
                textAlign = TextAlign.Start,
                fontFamily = Fonts.enduranceFamily,
                color = Colors.black,
                fontSize = Dimens.repoDescriptionTextSize,
            )

            DoubleSpacer()

        } // Column

    } // Card
}