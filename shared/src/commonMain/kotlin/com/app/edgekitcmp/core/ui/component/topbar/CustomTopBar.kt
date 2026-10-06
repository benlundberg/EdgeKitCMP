package com.app.edgekitcmp.core.ui.component.topbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.adamglin.phosphoricons.RegularGroup
import com.adamglin.phosphoricons.regular.ArrowLeft
import com.app.edgekitcmp.core.ui.theme.AppTheme
import com.app.edgekitcmp.core.ui.theme.bold

@Composable
fun CustomTopBar(
    title: String? = null,
    onBack: (() -> Unit)? = null,
    leadingAction: @Composable (() -> Unit)? = null,
    actions: @Composable (() -> Unit)? = null
) {
    TopAppBar(
        title = {
            title?.let { title ->
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineMedium.bold,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        navigationIcon = {
            onBack?.let { onBack ->
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = RegularGroup.ArrowLeft,
                        contentDescription = null,
                    )
                }
            } ?: leadingAction?.let { it() }
        },
        actions = {
            actions?.let { it() }
        },
        colors = topBarColors()
    )
}

@Composable
private fun topBarColors(isSystemInDarkTheme: Boolean = AppTheme.isDarkTheme): TopAppBarColors =
    if (isSystemInDarkTheme) {
        TopAppBarDefaults.topAppBarColors(
            containerColor = AppTheme.color.darkBackground,
            navigationIconContentColor = AppTheme.color.white,
            actionIconContentColor = AppTheme.color.white,
            titleContentColor = AppTheme.color.white
        )
    } else {
        TopAppBarDefaults.topAppBarColors(
            containerColor = AppTheme.color.lightBackground,
            navigationIconContentColor = AppTheme.color.black,
            actionIconContentColor = AppTheme.color.black,
            titleContentColor = AppTheme.color.black
        )
    }

@Composable
@Preview(showBackground = true)
private fun CustomTopBarLightPreview() {
    AppTheme {
        CustomTopBar(
            title = "Title",
            onBack = {},
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun CustomTopBarDarkPreview() {
    AppTheme(darkTheme = true) {
        CustomTopBar(
            title = "Title",
            onBack = {},
        )
    }
}