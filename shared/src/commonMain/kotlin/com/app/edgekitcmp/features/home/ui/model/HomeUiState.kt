package com.app.edgekitcmp.features.home.ui.model

import com.app.edgekitcmp.ui.model.ViewState

data class HomeUiState(
    val options: List<UiHomeOptionModel> = listOf(
        UiHomeOptionModel("Settings", UiHomeOptionType.Settings),
        UiHomeOptionModel("Snackbar", UiHomeOptionType.Snackbar)
    )
) : ViewState