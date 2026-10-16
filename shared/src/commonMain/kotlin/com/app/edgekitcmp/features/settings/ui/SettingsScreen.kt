package com.app.edgekitcmp.features.settings.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Regular
import com.adamglin.phosphoricons.regular.Check
import com.app.edgekitcmp.core.ui.component.scaffold.CustomScaffold
import com.app.edgekitcmp.core.ui.preview.BasePreview
import com.app.edgekitcmp.core.ui.theme.AppTheme
import com.app.edgekitcmp.core.ui.theme.bold
import com.app.edgekitcmp.features.settings.domain.model.AppLanguage
import com.app.edgekitcmp.features.settings.domain.model.ThemeMode
import com.app.edgekitcmp.features.settings.ui.component.label
import com.app.edgekitcmp.resources.Res
import com.app.edgekitcmp.resources.settings_language_title
import com.app.edgekitcmp.resources.settings_theme_title
import com.app.edgekitcmp.resources.settings_title
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = koinViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.stateFlow.collectAsState()

    CustomScaffold(
        title = stringResource(Res.string.settings_title),
        onBack = onBack,
        snackbarEventFlow = viewModel.eventsFlow,
    ) {
        SettingsContent(
            themeMode = state.themeMode,
            onChangeTheme = viewModel::onChangeTheme,
            appLanguage = state.appLanguage,
            onChangeLanguage = viewModel::onChangeLanguage
        )
    }
}

@Composable
private fun SettingsContent(
    themeMode: ThemeMode,
    onChangeTheme: (ThemeMode) -> Unit,
    appLanguage: AppLanguage,
    onChangeLanguage: (AppLanguage) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(AppTheme.spacings.defaultPagePadding)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacings.m)
    ) {
        Text(
            text = stringResource(Res.string.settings_theme_title).uppercase(),
            style = MaterialTheme.typography.labelLarge.bold
        )
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ThemeMode.entries.forEachIndexed { index, mode ->
                SegmentedButton(
                    selected = themeMode == mode,
                    onClick = { onChangeTheme(mode) },
                    shape = SegmentedButtonDefaults.itemShape(index, ThemeMode.entries.size),
                ) { Text(text = mode.label(), style = MaterialTheme.typography.bodyLarge) }
            }
        }

        Text(
            text = stringResource(Res.string.settings_language_title).uppercase(),
            style = MaterialTheme.typography.labelLarge.bold
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacings.xxs)
        ) {
            AppLanguage.entries.forEach { language ->
                Row(
                    modifier = Modifier
                        .clickable(
                            enabled = true
                        ) {
                            onChangeLanguage(language)
                        }
                        .background(MaterialTheme.colorScheme.surfaceContainer)
                        .padding(AppTheme.spacings.m),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = language.label(),
                        style = MaterialTheme.typography.bodyLarge
                    )

                    if (appLanguage == language) {
                        Icon(PhosphorIcons.Regular.Check, null)
                    }
                }
            }
        }
    }
}


@Composable
@Preview
private fun SettingsLightPreview() {
    BasePreview {
        SettingsContent(
            themeMode = ThemeMode.System,
            onChangeTheme = {},
            appLanguage = AppLanguage.System,
            onChangeLanguage = {}
        )
    }
}

@Composable
@Preview
private fun SettingsDarkPreview() {
    BasePreview(darkTheme = true) {
        SettingsContent(
            themeMode = ThemeMode.Dark,
            onChangeTheme = {},
            appLanguage = AppLanguage.English,
            onChangeLanguage = {}
        )
    }
}

