package io.github.kunal26das.cred.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.kunal26das.cred.theme.Colors

@Composable
fun CollapsedState(
    modifier: Modifier = Modifier,
    text: String = "",
    buttonColor: Color = Colors.Icon,
    onButtonClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier,
            text = text,
        )
        CircularIconButton(
            modifier = Modifier,
            buttonColor = buttonColor,
            imageVector = Icons.Filled.KeyboardArrowDown,
            onClick = onButtonClick,
        )
    }
}