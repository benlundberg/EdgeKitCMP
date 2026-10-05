package com.app.edgekitcmp.ui.component.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.edgekitcmp.ui.component.snackbar.CustomSnackBarEffect
import com.app.edgekitcmp.ui.component.snackbar.CustomSnackBarHost
import com.app.edgekitcmp.ui.component.snackbar.CustomSnackBarHostState
import com.app.edgekitcmp.ui.component.topbar.CustomTopBar
import com.app.edgekitcmp.ui.model.OneTimeEvent
import com.app.edgekitcmp.ui.theme.AppTheme
import kotlinx.coroutines.flow.Flow

@Composable
fun CustomScaffold(
    title: String? = null,
    onBack: (() -> Unit)? = null,
    snackbarEventFlow: Flow<OneTimeEvent>? = null,
    onEvent: ((OneTimeEvent) -> Unit)? = null,
    leadingAction: @Composable (() -> Unit)? = null,
    actions: @Composable (() -> Unit)? = null,
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    val snackBarHostState: CustomSnackBarHostState = remember { CustomSnackBarHostState() }

    CustomSnackBarEffect(
        snackbarEventFlow = snackbarEventFlow,
        snackBarHostState = snackBarHostState,
        onEvent = onEvent
    )

    // Calculate if top bar should be displayed
    val shouldDisplayTopBar = title != null || onBack != null || leadingAction != null || actions != null

    Scaffold(
        topBar = {
            if (shouldDisplayTopBar) {
                CustomTopBar(
                    title = title,
                    onBack = onBack,
                    leadingAction = leadingAction,
                    actions = actions
                )
            }
        },
        snackbarHost = {
            CustomSnackBarHost(snackBarHostState = snackBarHostState)
        },
        floatingActionButton = { floatingActionButton() },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            content(innerPadding)
        }
    }
}

@Composable
@Preview
private fun CustomScaffoldLightPreview() {
    AppTheme {
        CustomScaffold(
            title = "Hello World"
        ) {
            Text("Hello World")
        }
    }
}

@Composable
@Preview
private fun CustomScaffoldDarkPreview() {
    AppTheme(darkTheme = true) {
        CustomScaffold(
            title = "Hello World"
        ) {
            Text("Hello World")
        }
    }
}