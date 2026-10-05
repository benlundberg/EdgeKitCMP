package com.app.edgekitcmp.ui.preview

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.app.edgekitcmp.ui.theme.AppTheme

@Composable
fun BasePreview(darkTheme: Boolean = false, content: @Composable () -> Unit) {
    AppTheme(
        darkTheme = darkTheme
    ) {
        Surface {
            content()
        }
    }
}