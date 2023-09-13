package io.github.kunal26das.cred.sheet

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.kunal26das.cred.compose.ExpandedState
import io.github.kunal26das.cred.stack.Sheet
import io.github.kunal26das.cred.stack.State
import io.github.kunal26das.cred.stack.StateContent
import io.github.kunal26das.cred.theme.Colors

class ThirdSheet(
    onFinish: () -> Unit = {},
) : Sheet(
    color = Colors.Quaternary,
    expandedContent = ThirdSheetExpandedContent(onFinish)
)

private class ThirdSheetExpandedContent(
    private val onFinish: () -> Unit = {}
) : StateContent {
    @Composable
    override fun Content(index: Int, state: State, modifier: Modifier) {
        ExpandedState(
            modifier = modifier,
            text = "Step Three",
            buttonText = "Finish",
            onButtonClick = onFinish,
        )
    }
}