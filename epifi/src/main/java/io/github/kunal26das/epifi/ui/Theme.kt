package io.github.kunal26das.epifi.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import io.github.kunal26das.epifi.R

/** Original OMDB brand palette (always-dark theme). */
object EpifiColors {
    val Background = Color(0xFF282828)
    val SearchBox = Color(0xFF383838)
    val ButtonSelected = Color(0xFF555555)
    val Hint = Color(0xFF646464)
    val Subtitle = Color(0xFF8D8D8D)
    val Cream = Color(0xFFFFF2D5)
    val CreamText = Color(0xFF333333)
    val PosterScrim = Color(0xFF333333)
}

val Gilroy = FontFamily(
    Font(R.font.gilroy_bold, FontWeight.Bold),
    Font(R.font.gilroy_semi_bold, FontWeight.SemiBold),
)

val Inter = FontFamily(Font(R.font.inter_regular))

@Composable
fun EpifiTheme(content: @Composable () -> Unit) {
    // The OMDB app was always dark regardless of system setting.
    @Suppress("UNUSED_EXPRESSION") isSystemInDarkTheme()
    MaterialTheme(
        colorScheme = darkColorScheme(
            background = EpifiColors.Background,
            surface = EpifiColors.Background,
            onBackground = Color.White,
            onSurface = Color.White,
            primary = Color.White,
        ),
        content = content,
    )
}
