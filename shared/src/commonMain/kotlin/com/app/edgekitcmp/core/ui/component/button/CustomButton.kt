package com.app.edgekitcmp.core.ui.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adamglin.phosphoricons.RegularGroup
import com.adamglin.phosphoricons.regular.Alarm
import com.adamglin.phosphoricons.regular.Axe
import com.app.edgekitcmp.core.ui.theme.AppTheme
import com.app.edgekitcmp.core.ui.theme.bold

private const val BUTTON_HEIGHT = 56

sealed class ButtonStyle {
    object Primary : ButtonStyle()
    object Secondary : ButtonStyle()
    object Text : ButtonStyle()
}

@Composable
fun CustomButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    buttonStyle: ButtonStyle = ButtonStyle.Primary,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    buttonColors: ButtonColors? = null,
) {
    val colors = buttonColors ?: when (buttonStyle) {
        ButtonStyle.Secondary -> {
            ButtonDefaults.buttonColors(
                containerColor = AppTheme.color.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            )
        }

        ButtonStyle.Text -> {
            ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground
            )
        }

        else -> {
            ButtonDefaults.buttonColors(
                containerColor = AppTheme.color.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }

    Button(
        onClick = onClick,
        modifier = modifier
            .then(
                Modifier
                    .defaultMinSize(minHeight = BUTTON_HEIGHT.dp)
            ),
        enabled = enabled,
        colors = colors,
        shape = AppTheme.shape.buttonShape,
        contentPadding = if (buttonStyle == ButtonStyle.Text) {
            PaddingValues(horizontal = 0.dp)
        } else {
            ButtonDefaults.ContentPadding
        }
    ) {
        leadingIcon?.let { icon ->
            icon()
            Spacer(modifier = Modifier.width(
                AppTheme.spacings.s))
        }
        Text(
            text = label.uppercase(),
            style = MaterialTheme.typography.titleMedium.bold,
            textAlign = TextAlign.Center
        )
        trailingIcon?.let { icon ->
            Spacer(modifier = Modifier.width(
                AppTheme.spacings.s))
            icon()
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun CustomButtonsLightPreview() {
    AppTheme {
        Column(
            modifier = Modifier.padding(
                AppTheme.spacings.m
            ),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacings.m)
        ) {
            CustomButton(
                label = "Primary",
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            )
            CustomButton(
                label = "Secondary",
                buttonStyle = ButtonStyle.Secondary,
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            )
            CustomButton(
                label = "Text",
                buttonStyle = ButtonStyle.Text,
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            )
            CustomButton(
                label = "Leading Icon",
                buttonStyle = ButtonStyle.Secondary,
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = RegularGroup.Alarm,
                        contentDescription = null
                    )
                }
            )
            CustomButton(
                label = "Trailing icon",
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Icon(
                        imageVector = RegularGroup.Axe,
                        contentDescription = null
                    )
                }
            )
        }
    }
}