package io.github.kunal26das.cred.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.kunal26das.cred.entity.Button

@Composable
fun ExpandedState(
    modifier: Modifier = Modifier,
    text: String = "",
    buttonText: String = "",
    onButtonClick: () -> Unit = {},
) {
    Box(
        modifier = modifier,
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 40.dp),
            text = text,
        )
        BottomButton(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            button = Button(buttonText),
            onButtonClick = onButtonClick,
        )
    }
}