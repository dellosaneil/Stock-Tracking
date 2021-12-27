package com.example.compose.compose_base


import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.staticCompositionLocalOf

val ComposeDarkColorPalette = ComposeColors()

val ComposeLightColorPalette = ComposeColors()

class ComposeColors()


/**
 * Color state variable declaration
 * This variable is needed to save our current mode(dark/light)
 */
val LocalComposeColor = staticCompositionLocalOf {
    ComposeLightColorPalette
}

fun prepareCollaborateColors(
    isDarkTheme: Boolean = false,
): ComposeColors {
    return if (isDarkTheme) ComposeDarkColorPalette else ComposeLightColorPalette
}

fun provideComposeColorsProvider(colors: ComposeColors): ProvidedValue<ComposeColors> {
    return LocalComposeColor provides colors
}

object ComposeColorFactory {
    private val colors: ComposeColors
        @Composable
        get() = LocalComposeColor.current

    @Composable
    fun getComposeColors() = colors
}
