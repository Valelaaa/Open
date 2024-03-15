package com.example.openmindproject.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.openmind.ui.theme.DarkBlue40
import com.example.openmind.ui.theme.IconColor
import com.example.openmind.ui.theme.LightThemeBackgroundColor
import com.example.openmind.ui.theme.Pink80
import com.example.openmind.ui.theme.SteelBlue60
import com.example.openmind.ui.theme.Typography


private val DarkColorScheme = darkColorScheme(
    primary = Color.White,
    secondary = DarkBlue40,
    tertiary = Pink80,
    background = Color.Black,
)

open class NavigationIconStyle(
    var iconColor: Color = IconColor,
    var modifier: Modifier = Modifier.size(24.dp)
) {
    companion object {
        fun defaultIconStyle() = NavigationIconStyle(
            iconColor = IconColor,
            modifier = Modifier.size(24.dp)
        )
    }

}

private val LightColorScheme = lightColorScheme(
    primary = DarkBlue40,
    secondary = SteelBlue60,
    tertiary = DarkBlue40,
    background = LightThemeBackgroundColor
//    onPrimary: Color = ColorLightTokens.OnPrimary,
//primaryContainer: Color = ColorLightTokens.PrimaryContainer,
//onPrimaryContainer: Color = ColorLightTokens.OnPrimaryContainer,
//inversePrimary: Color = ColorLightTokens.InversePrimary,
//secondary: Color = ColorLightTokens.Secondary,
//onSecondary: Color = ColorLightTokens.OnSecondary,
//secondaryContainer: Color = ColorLightTokens.SecondaryContainer,
//onSecondaryContainer: Color = ColorLightTokens.OnSecondaryContainer,
//tertiary: Color = ColorLightTokens.Tertiary,
//onTertiary: Color = ColorLightTokens.OnTertiary,
//tertiaryContainer: Color = ColorLightTokens.TertiaryContainer,
//onTertiaryContainer: Color = ColorLightTokens.OnTertiaryContainer,
//background: Color = ColorLightTokens.Background,
//onBackground: Color = ColorLightTokens.OnBackground,
//surface: Color = ColorLightTokens.Surface,
//onSurface: Color = ColorLightTokens.OnSurface,
//surfaceVariant: Color = ColorLightTokens.SurfaceVariant,
//onSurfaceVariant: Color = ColorLightTokens.OnSurfaceVariant,
//surfaceTint: Color = primary,
//inverseSurface: Color = ColorLightTokens.InverseSurface,
//inverseOnSurface: Color = ColorLightTokens.InverseOnSurface,
//error: Color = ColorLightTokens.Error,
//onError: Color = ColorLightTokens.OnError,
//errorContainer: Color = ColorLightTokens.ErrorContainer,
//onErrorContainer: Color = ColorLightTokens.OnErrorContainer,
//outline: Color = ColorLightTokens.Outline,
//outlineVariant: Color = ColorLightTokens.OutlineVariant,
//scrim: Color = ColorLightTokens.Scrim,

)

@Composable
fun OpenMindProjectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit

) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )

}