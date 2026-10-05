package com.app.edgekitcmp.features.settings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.edgekitcmp.features.settings.domain.model.ThemeMode
import com.app.edgekitcmp.features.settings.ui.component.label
import com.app.edgekitcmp.ui.component.scaffold.CustomScaffold
import com.app.edgekitcmp.ui.preview.BasePreview
import com.app.edgekitcmp.ui.theme.AppTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = koinViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.stateFlow.collectAsState()

    CustomScaffold(
        title = "Settings",
        onBack = onBack,
        snackbarEventFlow = viewModel.eventsFlow,
    ) {
        SettingsContent(
            themeMode = state.themeMode,
            onChangeTheme = viewModel::onChangeTheme
        )
    }
}

@Composable
private fun SettingsContent(themeMode: ThemeMode, onChangeTheme: (ThemeMode) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppTheme.spacings.m),
        ) {
            ThemeMode.entries.forEachIndexed { index, mode ->
                SegmentedButton(
                    selected = themeMode == mode,
                    onClick = { onChangeTheme(mode) },
                    shape = SegmentedButtonDefaults.itemShape(index, ThemeMode.entries.size),
                ) { Text(mode.label()) }
            }
        }
        HorizontalDivider()
    }
}



@Composable
@Preview
private fun SettingsLightPreview() {
    BasePreview {
        SettingsContent(
            themeMode = ThemeMode.System,
            onChangeTheme = {}
        )
    }
}

@Composable
@Preview
private fun SettingsDarkPreview() {
    BasePreview(darkTheme = true) {
        SettingsContent(
            themeMode = ThemeMode.Dark,
            onChangeTheme = {}
        )
    }
}

