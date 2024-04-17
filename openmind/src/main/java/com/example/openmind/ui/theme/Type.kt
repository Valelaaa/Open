package com.example.openmind.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.openmind.R

val FontFamily.Companion.ManropeRegularW400: FontFamily
    get() { return FontFamily(Font(R.font.manrope_regular)) }
val FontFamily.Companion.ManropeSemiBoldW600: FontFamily
    get() { return FontFamily(Font(R.font.manrope_semibold)) }
val FontFamily.Companion.ManropeExtraBoldW800: FontFamily
    get() { return FontFamily(Font(R.font.manrope_extrabold)) }

val FontFamily.Companion.ManropeBoldW700: FontFamily
    get() { return FontFamily(Font(R.font.manrope_bold700)) }

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.ManropeBoldW700,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
//    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.ManropeExtraBoldW800,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.ManropeBoldW700,
        fontWeight = FontWeight.W700,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily.ManropeSemiBoldW600,
        fontWeight = FontWeight.W600,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
//    */
)