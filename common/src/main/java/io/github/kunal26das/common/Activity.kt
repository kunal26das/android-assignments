package io.github.kunal26das.common

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

abstract class Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Theme {
                Content()
            }
        }
    }

    @Composable
    abstract fun Content()

    @Composable
    fun Theme(
        useDarkTheme: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit
    ) {
        val dynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
        val colors = when {
            dynamicColor && useDarkTheme -> dynamicDarkColorScheme(LocalContext.current)
            dynamicColor && !useDarkTheme -> dynamicLightColorScheme(LocalContext.current)
            else -> MaterialTheme.colorScheme
        }
        MaterialTheme(
            colorScheme = colors,
            content = content
        )
    }
}