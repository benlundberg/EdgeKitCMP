package com.app.edgekitcmp.core.ui.model

import org.jetbrains.compose.resources.StringResource

interface UiText {
    data class Plain(val value: String) : UiText
    class Resource(val res: StringResource, vararg val args: Any) : UiText
}