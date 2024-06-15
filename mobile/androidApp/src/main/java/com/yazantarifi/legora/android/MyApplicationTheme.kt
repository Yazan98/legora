package com.yazantarifi.legora.android

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat

val Typography = Typography(
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
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
    */
)

val PrimaryColorLight = Color(0xFF000000)
val SeconderyColorLight = Color(0xFF222222)
val TertiaryColorLight = Color(0xFF9772EE)
val ScreenBackgroundLight = Color(0xFFFFFFFF)

val PrimaryColorDark = Color(0xFF000000)
val SeconderyColorDark = Color(0xFFECECEC)
val TertiaryColorDark = Color(0xFFD0BCFF)
val ScreenBackgroundDark = Color(0xFF4B4B4B)

val SeconderyTextColor = Color(0xFF616161)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryColorDark,
    secondary = SeconderyColorDark,
    tertiary = TertiaryColorDark,
    background = ScreenBackgroundDark,
    onBackground = Color(0xFF101A30),
    onSecondary = Color(0xFF939BB5),
    primaryContainer = Color(0xFF6D85FF),
    onPrimary = Color(0xFFFFFFFF),
    outline = Color(0xFFF9FAFD),
    onPrimaryContainer = Color(0xFF6D85FF),
    onSecondaryContainer = Color(0xFFDEE4F6),
    surfaceVariant = Color(0xFFBEC4D4),
    onSurfaceVariant = Color(0xFF000000),
    inverseOnSurface = Color(0xFFebebeb),
    inversePrimary = Color(0xFFA8AEBF),
    secondaryContainer = Color(0xFFBDC1CE),
    onSurface = Color(0xFF80889D)
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryColorLight,
    secondary = SeconderyColorLight,
    tertiary = TertiaryColorLight,
    background = ScreenBackgroundLight,
    onBackground = Color(0xFF101A30),
    onSecondary = Color(0xFF939BB5),
    primaryContainer = Color(0xFF6D85FF),
    onPrimary = Color(0xFF000000),
    outline = Color(0xFFF9FAFD),
    onPrimaryContainer = Color(0xFF6D85FF),
    onSecondaryContainer = Color(0xFFDEE4F6),
    surfaceVariant = Color(0xFFBEC4D4),
    onSurfaceVariant = Color(0xFFFFFFFF),
    inverseOnSurface = Color(0xFFebebeb),
    inversePrimary = Color(0xFFA8AEBF),
    secondaryContainer = Color(0xFFBDC1CE),
    onSurface = Color(0xFF80889D)
)

@Composable
fun LegoraAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }

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