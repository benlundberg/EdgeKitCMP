package com.app.edgekitcmp.features.snackbar.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.edgekitcmp.ui.component.button.CustomButton
import com.app.edgekitcmp.ui.component.scaffold.CustomScaffold
import com.app.edgekitcmp.ui.component.snackbar.SnackBarType
import com.app.edgekitcmp.ui.theme.AppTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SnackBarScreen(viewModel: SnackBarViewModel = koinViewModel(), onBack: () -> Unit) {

    CustomScaffold(
        title = "Snack bar",
        snackbarEventFlow = viewModel.eventsFlow,
        onBack = onBack
    ) {
        SnackBarContent(
            onClick = viewModel::onShowSnackBar
        )
    }
}

@Composable
private fun SnackBarContent(
    onClick: (SnackBarType) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(AppTheme.spacings.defaultPagePadding),
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacings.m)
    ) {
        CustomButton(
            label = "Information",
            onClick = { onClick(SnackBarType.INFO) },
            modifier = Modifier.fillMaxWidth()
        )
        CustomButton(
            label = "Warning",
            onClick = { onClick(SnackBarType.WARNING) },
            modifier = Modifier.fillMaxWidth()
        )
        CustomButton(
            label = "Success",
            onClick = { onClick(SnackBarType.SUCCESS) },
            modifier = Modifier.fillMaxWidth()
        )
        CustomButton(
            label = "Error",
            onClick = { onClick(SnackBarType.ERROR) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}