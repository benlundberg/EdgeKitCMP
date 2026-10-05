package com.app.edgekitcmp.ui.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

@Immutable
data class Shape(
    val buttonShape: CornerBasedShape = RoundedCornerShape(8.dp)
)

val LocalShape = staticCompositionLocalOf { Shape() }