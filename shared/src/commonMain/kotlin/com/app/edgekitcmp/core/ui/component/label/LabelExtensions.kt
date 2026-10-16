package com.app.edgekitcmp.core.ui.component.label

import androidx.compose.runtime.Composable
import com.app.edgekitcmp.core.ui.model.UiText
import org.jetbrains.compose.resources.stringResource

@Composable
fun UiText.asString(): String = when (this) {
    is UiText.Plain -> value
    is UiText.Resource -> stringResource(res, *args)
    else -> ""
}