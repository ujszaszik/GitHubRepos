package com.withintent.githubrepos.features.repos.details.ui

import androidx.compose.runtime.Composable
import com.withintent.compose.button.Counter
import com.withintent.compose.resources.Images
import com.withintent.githubrepos.formatter.NumericFormatter

@Composable
fun StarsCounter(value: Number) {
    Counter(
        value = value,
        formatter = { NumericFormatter.format(it) },
        image = { Images.StarsImage() },
    )
}

@Composable
fun WatchingCounter(value: Number) {
    Counter(
        value = value,
        formatter = { NumericFormatter.format(it) },
        image = { Images.WatchersImage() },
    )
}

@Composable
fun ForksCounter(value: Number) {
    Counter(
        value = value,
        formatter = { NumericFormatter.format(it) },
        image = { Images.ForksImage() },
    )
}