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
        fontFamily = FontFamily.ManropeRegularW400,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
//    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.ManropeExtraBoldW800,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
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