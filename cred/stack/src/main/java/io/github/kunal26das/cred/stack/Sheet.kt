package io.github.kunal26das.cred.stack

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

open class Sheet(
    val color: Color,
    val peekHeight: Dp = 100.dp,
    val cornerRadius: Dp = 24.dp,
    val expandedContent: StateContent? = null,
    val collapsedContent: StateContent? = null,
) {
    @Composable
    internal fun height(index: Int): Dp {
        val configuration = LocalWindowInfo.current
        val screenHeight = configuration.containerSize.height.dp
        return screenHeight - peekHeight * (index + 1)
    }
}