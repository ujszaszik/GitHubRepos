package com.withintent.githubrepos.features.repos.details.ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.withintent.compose.resources.Colors
import com.withintent.compose.resources.Dimens

@Composable
fun RepoDetailsTopicChip(tag: String) {

    Button(
        modifier = Modifier
            .padding(Dimens.paddingHalf)
            .height(Dimens.chipHeight),
        shape = RoundedCornerShape(Dimens.buttonCornerRadius),
        colors = ButtonDefaults.buttonColors(backgroundColor = Colors.lightBlue),
        onClick = { Unit }
    ) {
        Text(
            text = tag,
            fontSize = Dimens.smallTextSize,
            color = Colors.blue
        )
    }
}