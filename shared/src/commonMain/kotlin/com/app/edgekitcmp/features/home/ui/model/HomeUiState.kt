package com.app.edgekitcmp.features.home.ui.model

import com.app.edgekitcmp.core.ui.model.UiText
import com.app.edgekitcmp.core.ui.model.ViewState
import com.app.edgekitcmp.resources.Res
import com.app.edgekitcmp.resources.settings_title

data class HomeUiState(
    val options: List<UiHomeOptionModel> = listOf(
        UiHomeOptionModel(UiText.Resource(Res.string.settings_title), UiHomeOptionType.Settings),
        UiHomeOptionModel(UiText.Plain("Snackbar"), UiHomeOptionType.Snackbar)
    )
) : ViewState