package io.github.kunal26das.cred.compose

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.kunal26das.cred.theme.Colors

@Composable
fun CircularIconButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    imageVector: ImageVector,
    iconTint: Color = Colors.Icon,
    buttonColor: Color = Colors.Secondary,
    onClick: () -> Unit = {}
) {
    IconButton(
        modifier = modifier,
        content = {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                tint = iconTint,
            )
        },
        colors = IconButtonDefaults.filledIconButtonColors(
            containerColor = buttonColor,
        ),
        enabled = enabled,
        onClick = onClick,
    )
}