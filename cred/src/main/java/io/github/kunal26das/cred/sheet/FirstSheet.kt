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

class FirstSheet : Sheet(
    color = Colors.Secondary,
    expandedContent = FirstSheetExpandedContent(),
    collapsedContent = FirstSheetCollapsedContent(),
)

private class FirstSheetExpandedContent : StateContent {
    @Composable
    override fun Content(index: Int, state: State, modifier: Modifier) {
        val coroutineScope = rememberCoroutineScope()
        ExpandedState(
            modifier = modifier,
            text = "Step One",
            buttonText = "Proceed to Step Two",
        ) {
            coroutineScope.launch {
                state.expandNext()
            }
        }
    }
}

private class FirstSheetCollapsedContent : StateContent {
    @Composable
    override fun Content(index: Int, state: State, modifier: Modifier) {
        val coroutineScope = rememberCoroutineScope()
        CollapsedState(
            modifier = modifier,
            text = "Step One Done",
            buttonColor = Colors.Secondary,
        ) {
            coroutineScope.launch {
                state.collapseAfter(index)
            }
        }
    }
}