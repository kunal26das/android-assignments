package io.github.kunal26das.cred.entity

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.kunal26das.cred.theme.Colors

data class Button(
    val text: String,
    val height: Dp = 64.dp,
    val cornerRadius: Dp = 24.dp,
    val color: Color = Colors.Button,
    val textColor: Color = Colors.ButtonText,
)