package com.app.edgekitcmp.features.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.edgekitcmp.features.home.ui.model.HomeUiState
import com.app.edgekitcmp.features.home.ui.model.UiHomeOptionModel
import com.app.edgekitcmp.features.home.ui.model.UiHomeOptionType
import com.app.edgekitcmp.core.ui.component.button.CustomButton
import com.app.edgekitcmp.core.ui.component.scaffold.CustomScaffold
import com.app.edgekitcmp.core.ui.preview.BasePreview
import com.app.edgekitcmp.core.ui.theme.AppTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = koinViewModel(),
    onOptionClick: (UiHomeOptionType) -> Unit
) {
    val state by homeViewModel.stateFlow.collectAsState()

    CustomScaffold(
        title = "Home"
    ) {
        HomeContent(
            options = state.options,
            onClick = onOptionClick
        )
    }
}

@Composable
private fun HomeContent(options: List<UiHomeOptionModel>, onClick: (UiHomeOptionType) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = AppTheme.spacings.defaultPagePadding,
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacings.m)
    ) {
        items(options) { option ->
            CustomButton(
                label = option.title,
                onClick = {
                    onClick(option.type)
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
@Preview
private fun HomeLightPreview() {
    BasePreview {
        HomeContent(
            options = HomeUiState().options,
            onClick = {}
        )
    }
}

@Composable
@Preview
private fun HomeDarkPreview() {
    BasePreview(darkTheme = true) {
        HomeContent(
            options = HomeUiState().options,
            onClick = {}
        )
    }
}