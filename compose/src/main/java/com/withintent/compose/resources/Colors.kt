package com.withintent.compose.resources

import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object Colors {

    val white = Color(0xFFFFFFFF)
    val blue = Color(0xFF3399CC)
    val lightBlue = Color(0xFFC7D9F0)
    val red = Color(0xFFFF6917)
    val gray = Color(0xFFC1C1C1)
    val steelGray = Color(0xFF767676)
    val black = Color(0xFF171515)


    @Composable
    fun inputFieldColors() = TextFieldDefaults.textFieldColors(
        textColor = black,
        disabledTextColor = gray,
        focusedLabelColor = black,
        unfocusedLabelColor = black,
        focusedIndicatorColor = black,
        unfocusedIndicatorColor = gray
    )
}