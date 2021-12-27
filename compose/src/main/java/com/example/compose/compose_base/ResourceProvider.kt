package com.example.compose.compose_base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember

@Composable
fun ProvideResources(
    isDarkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val collaborateColors = remember {
        prepareCollaborateColors(isDarkTheme = isDarkTheme)
    }

    CompositionLocalProvider(
        provideComposeColorsProvider(collaborateColors),
        provideComposeTypographyProvider(),
        content = content
    )
}
