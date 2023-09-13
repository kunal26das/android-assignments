package io.github.kunal26das.cred.stack

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

fun interface StateContent {
    @Composable fun Content(index: Int, state: State, modifier: Modifier)
}