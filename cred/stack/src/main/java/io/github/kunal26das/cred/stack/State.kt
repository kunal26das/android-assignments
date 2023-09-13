package io.github.kunal26das.cred.stack

import androidx.activity.OnBackPressedCallback
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
data class State internal constructor(
    internal val sheets: List<Sheet>,
    internal val sheetStates: List<SheetState>,
) {

    suspend fun collapse(callback: () -> Unit) {
        sheetStates.indexOfLast {
            it.currentValue != SheetValue.Hidden
        }.let { index ->
            when (index) {
                0 -> callback.invoke()
                else -> sheetStates[index].hide()
            }
        }
    }

    suspend fun collapseAfter(index: Int) = withContext(Dispatchers.Main) {
        sheetStates.takeLast(sheetStates.size - index - 1).map {
            async { it.hide() }
        }.forEach {
            it.await()
        }
    }

    suspend fun expandNext() {
        sheetStates.indexOfFirst {
            it.currentValue == SheetValue.Hidden
        }.let {
            if (it >= 0) {
                sheetStates[it].show()
            }
        }
    }

    fun getOnBackPressedCallback(
        coroutineScope: CoroutineScope,
        enabled: Boolean = true,
        callback: () -> Unit,
    ) = object : OnBackPressedCallback(enabled) {
        override fun handleOnBackPressed() {
            coroutineScope.launch {
                collapse(callback)
            }
        }
    }
}