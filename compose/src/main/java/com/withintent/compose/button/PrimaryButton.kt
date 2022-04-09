package com.withintent.compose.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.withintent.compose.resources.Colors

@Composable
fun PrimaryButton(
    label: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    DefaultButton(
        label = label,
        backgroundColor = Colors.black,
        textColor = Colors.white,
        icon = icon,
        iconTint = Colors.white,
        onClick = onClick
    )
}