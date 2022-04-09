package com.withintent.compose.button

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.withintent.compose.resources.Colors
import com.withintent.compose.resources.Dimens

@Composable
fun Counter(
    value: Number,
    formatter: (Number) -> String = { it.toString() },
    image: @Composable () -> Unit,
) {

    Card(
        modifier = Modifier.padding(horizontal = Dimens.paddingDouble),
        shape = RoundedCornerShape(Dimens.buttonCornerRadius),
        elevation = Dimens.zero
    ) {

        Row {

            image()

            Spacer(Modifier.size(ButtonDefaults.IconSpacing))

            Text(
                text = formatter(value),
                color = Colors.steelGray,
                fontSize = Dimens.counterTextSize,
                fontWeight = FontWeight.Bold
            )
        }
    }
}