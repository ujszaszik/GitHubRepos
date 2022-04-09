package com.withintent.githubrepos.features.repos.search.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import com.withintent.compose.button.PrimaryButton
import com.withintent.compose.input.InputField
import com.withintent.compose.layout.CenteredColumn
import com.withintent.githubrepos.features.main.util.LocalKeyboardManager

@Composable
fun ReposSearchScreen(
    errorMessage: String? = null,
    onTextChanged: (String) -> Unit,
    onSearchRequested: () -> Unit
) {

    val keyboardManager = LocalKeyboardManager.current

    CenteredColumn {

        InputField(
            label = ReposSearchTexts.SEARCH_HINT,
            errorMessage = errorMessage,
            onTextChanged = { onTextChanged(it) }
        )

        PrimaryButton(
            label = ReposSearchTexts.SEARCH_BUTTON_LABEL,
            onClick = {
                keyboardManager.hide()
                onSearchRequested()
            },
            icon = Icons.Default.Search
        )
    }
}