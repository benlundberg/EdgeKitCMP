package com.app.edgekitcmp.ui.component.snackbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adamglin.phosphoricons.RegularGroup
import com.adamglin.phosphoricons.regular.CheckCircle
import com.adamglin.phosphoricons.regular.Info
import com.adamglin.phosphoricons.regular.Warning
import com.adamglin.phosphoricons.regular.WarningCircle
import com.adamglin.phosphoricons.regular.X
import com.app.edgekitcmp.ui.theme.AppTheme
import com.app.edgekitcmp.ui.theme.blackColor

private val SNACKBAR_HEIGHT = 56.dp

@Composable
fun CustomSnackBar(
    message: String,
    type: SnackBarType,
    duration: SnackbarDuration,
    onClose: () -> Unit
) {
    val config = when (type) {
        SnackBarType.INFO -> Pair(
            AppTheme.color.primary,
            RegularGroup.Info
        )

        SnackBarType.ERROR -> Pair(
            AppTheme.color.error,
            RegularGroup.WarningCircle
        )

        SnackBarType.SUCCESS -> Pair(
            AppTheme.color.success,
            RegularGroup.CheckCircle
        )

        SnackBarType.WARNING -> Pair(
            AppTheme.color.warning,
            RegularGroup.Warning
        )
    }

    Row(
        modifier = Modifier
            .background(config.first)
            .fillMaxWidth()
            .height(SNACKBAR_HEIGHT)
            .padding(horizontal = AppTheme.spacings.m),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacings.m),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = config.second, tint = AppTheme.color.black, contentDescription = null)
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium.blackColor,
            modifier = Modifier.weight(1f)
        )
        if (duration == SnackbarDuration.Indefinite) {
            IconButton(onClick = onClose) {
                Icon(
                    imageVector = RegularGroup.X,
                    tint = AppTheme.color.black,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
@Preview
private fun CustomSnackBarInfoPreview() {
    AppTheme {
        CustomSnackBar(
            message = "This is a snackbar info",
            type = SnackBarType.INFO,
            duration = SnackbarDuration.Short,
            onClose = {}
        )
    }
}

@Composable
@Preview
private fun CustomSnackBarErrorPreview() {
    AppTheme {
        CustomSnackBar(
            message = "This is a snackbar error",
            type = SnackBarType.ERROR,
            duration = SnackbarDuration.Short,
            onClose = {}
        )
    }
}

@Composable
@Preview
private fun CustomSnackBarSuccessPreview() {
    AppTheme {
        CustomSnackBar(
            message = "This is a snackbar success",
            type = SnackBarType.SUCCESS,
            duration = SnackbarDuration.Short,
            onClose = {}
        )
    }
}

@Composable
@Preview
private fun CustomSnackBarWarningPreview() {
    AppTheme {
        CustomSnackBar(
            message = "This is a snackbar warning",
            type = SnackBarType.WARNING,
            duration = SnackbarDuration.Short,
            onClose = {}
        )
    }
}