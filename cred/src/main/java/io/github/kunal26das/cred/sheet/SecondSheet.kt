package io.github.kunal26das.cred.sheet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import io.github.kunal26das.cred.compose.CollapsedState
import io.github.kunal26das.cred.compose.ExpandedState
import io.github.kunal26das.cred.theme.Colors
import io.github.kunal26das.stack.Sheet
import io.github.kunal26das.stack.State
import io.github.kunal26das.stack.StateContent
import kotlinx.coroutines.launch

class SecondSheet : Sheet(
    color = Colors.Tertiary,
    expandedContent = SecondSheetExpandedContent(),
    collapsedContent = SecondSheetCollapsedContent(),
)

private class SecondSheetExpandedContent : StateContent {
    @Composable
    override fun Content(index: Int, state: State, modifier: Modifier) {
        val coroutineScope = rememberCoroutineScope()
        ExpandedState(
            modifier = modifier,
            text = "Step Two",
            buttonText = "Proceed to Step Three",
        ) {
            coroutineScope.launch {
                state.expandNext()
            }
        }
    }
}

private class SecondSheetCollapsedContent : StateContent {
    @Composable
    override fun Content(index: Int, state: State, modifier: Modifier) {
        val coroutineScope = rememberCoroutineScope()
        CollapsedState(
            modifier = modifier,
            text = "Step Two Done",
            buttonColor = Colors.Tertiary,
        ) {
            coroutineScope.launch {
                state.collapseAfter(index)
            }
        }
    }
}