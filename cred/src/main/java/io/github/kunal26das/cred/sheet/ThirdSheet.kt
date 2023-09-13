package io.github.kunal26das.cred.sheet

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.kunal26das.cred.compose.ExpandedState
import io.github.kunal26das.cred.theme.Colors
import io.github.kunal26das.stack.Sheet
import io.github.kunal26das.stack.State
import io.github.kunal26das.stack.StateContent

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