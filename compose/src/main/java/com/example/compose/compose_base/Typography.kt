package com.example.compose.compose_base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.compose.R

class ComposeTypography internal constructor(
    val bold32: TextStyle,
    val bold28: TextStyle,
    val bold24: TextStyle,
    val bold20: TextStyle,
    val bold16: TextStyle,
    val bold14: TextStyle,
    val bold12: TextStyle,
    val bold10: TextStyle,
    val semiBold16: TextStyle,
    val semiBold14: TextStyle,
    val semiBold12: TextStyle,
    val semiBold10: TextStyle,
    val regular20: TextStyle,
    val regular16: TextStyle,
    val regular14: TextStyle,
    val regular12: TextStyle,
    val regular10: TextStyle,
) {
    constructor(
        defaultFontFamily: FontFamily = FontFamily(
            Font(R.font.open_sans_bold, FontWeight.Bold),
            Font(R.font.open_sans_regular, FontWeight.Normal),
            Font(R.font.open_sans_semi_bold, FontWeight.SemiBold)
        ),
        bold32: TextStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            lineHeight = 48.sp
        ),
        bold28: TextStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            lineHeight = 42.sp
        ),
        bold24: TextStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeight = 36.sp
        ),
        bold20: TextStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            lineHeight = 30.sp
        ),
        bold16: TextStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 24.sp
        ),
        bold14: TextStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 21.sp
        ),
        bold12: TextStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            lineHeight = 18.sp
        ),
        bold10: TextStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp,
            lineHeight = 15.sp
        ),
        semiBold16: TextStyle = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            lineHeight = 24.sp
        ),
        semiBold14: TextStyle = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            lineHeight = 21.sp
        ),
        semiBold12: TextStyle = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            lineHeight = 18.sp
        ),
        semiBold10: TextStyle = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 10.sp,
            lineHeight = 15.sp
        ),
        regular20: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 20.sp
        ),
        regular16: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            lineHeight = 24.sp
        ),
        regular14: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 21.sp
        ),
        regular12: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        ),
        regular10: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            lineHeight = 15.sp
        ),
    ) : this(
        bold32 = bold32.withDefaultFontFamily(defaultFontFamily),
        bold28 = bold28.withDefaultFontFamily(defaultFontFamily),
        bold24 = bold24.withDefaultFontFamily(defaultFontFamily),
        bold20 = bold20.withDefaultFontFamily(defaultFontFamily),
        bold16 = bold16.withDefaultFontFamily(defaultFontFamily),
        bold14 = bold14.withDefaultFontFamily(defaultFontFamily),
        bold12 = bold12.withDefaultFontFamily(defaultFontFamily),
        bold10 = bold10.withDefaultFontFamily(defaultFontFamily),
        semiBold16 = semiBold16.withDefaultFontFamily(defaultFontFamily),
        semiBold14 = semiBold14.withDefaultFontFamily(defaultFontFamily),
        semiBold12 = semiBold12.withDefaultFontFamily(defaultFontFamily),
        semiBold10 = semiBold10.withDefaultFontFamily(defaultFontFamily),
        regular20 = regular20.withDefaultFontFamily(defaultFontFamily),
        regular16 = regular16.withDefaultFontFamily(defaultFontFamily),
        regular14 = regular14.withDefaultFontFamily(defaultFontFamily),
        regular12 = regular12.withDefaultFontFamily(defaultFontFamily),
        regular10 = regular10.withDefaultFontFamily(defaultFontFamily)
    )
}


fun provideComposeTypographyProvider(): ProvidedValue<ComposeTypography> {
    return LocalComposeTypography provides ComposeTypographyFactory.createInstance()
}

fun TextStyle.withDefaultFontFamily(default: FontFamily): TextStyle {
    return if (fontFamily != null) this else copy(fontFamily = default)
}

val LocalComposeTypography = staticCompositionLocalOf {
    ComposeTypographyFactory.createInstance()
}

object ComposeTypographyFactory {
    private val typography: ComposeTypography
        @Composable
        get() = LocalComposeTypography.current

    @Composable
    fun getComposeTypography() = typography

    fun createInstance() = ComposeTypography()
}
