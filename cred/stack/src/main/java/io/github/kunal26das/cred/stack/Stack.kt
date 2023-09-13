@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.kunal26das.cred.stack

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
@SuppressLint("ComposableNaming")
fun Stack(sheet1: Sheet, sheet2: Sheet): State {
    return Stack(listOf(sheet1, sheet2))
}

@Composable
@SuppressLint("ComposableNaming")
fun Stack(sheet1: Sheet, sheet2: Sheet, sheet3: Sheet): State {
    return Stack(listOf(sheet1, sheet2, sheet3))
}

@Composable
@SuppressLint("ComposableNaming")
private fun Stack(sheets: List<Sheet>): State {
    val state = State(
        sheets = sheets,
        sheetStates = List(sheets.size) { index ->
            rememberStandardBottomSheetState(
                initialValue = when (index) {
                    0 -> SheetValue.Expanded
                    else -> SheetValue.Hidden
                },
                skipHiddenState = index == 0,
            )
        }
    )
    state.sheets.forEachIndexed { index, _ ->
        val sheet = state.sheets[index]
        val height = sheet.height(index)
        val sheetState = state.sheetStates[index]
        val coroutineScope = rememberCoroutineScope()
        val scaffoldState = when (sheetState.currentValue) {
            SheetValue.Expanded -> rememberBottomSheetScaffoldState()
            else -> rememberBottomSheetScaffoldState(sheetState)
        }
        BottomSheetScaffold(
            sheetContent = {
                val nextSheetState = state.sheetStates
                    .getOrNull(index + 1)?.currentValue
                val modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        onClick = {
                            coroutineScope.launch {
                                state.collapseAfter(index)
                            }
                        },
                        indication = null,
                    )
                when (nextSheetState) {
                    null, SheetValue.Hidden -> sheet.expandedContent?.Content(
                        index, state, modifier.height(height)
                    )

                    else -> sheet.collapsedContent?.Content(
                        index, state, modifier.height(sheet.peekHeight)
                    )
                }
            },
            sheetSwipeEnabled = false,
            scaffoldState = scaffoldState,
            sheetContainerColor = sheet.color,
            sheetShape = RoundedCornerShape(
                topStart = sheet.cornerRadius,
                topEnd = sheet.cornerRadius,
            ),
            sheetPeekHeight = height,
            sheetDragHandle = {},
            content = {},
        )
    }
    return state
}