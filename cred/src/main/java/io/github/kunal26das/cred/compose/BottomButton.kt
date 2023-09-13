package io.github.kunal26das.cred.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.kunal26das.cred.entity.Button

@Composable
fun BottomButton(
    modifier: Modifier = Modifier,
    button: Button,
    onButtonClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(button.height),
        shape = RoundedCornerShape(
            topStart = button.cornerRadius,
            topEnd = button.cornerRadius,
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = button.color,
        ),
        onClick = onButtonClick,
        content = {
            Text(
                text = button.text,
                color = button.textColor,
            )
        }
    )
}