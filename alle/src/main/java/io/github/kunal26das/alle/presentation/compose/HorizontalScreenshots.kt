package io.github.kunal26das.alle.presentation.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import io.github.kunal26das.alle.db.Screenshot

@Composable
fun HorizontalScreenshots(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    screenshotPadding: PaddingValues,
    screenshots: LazyPagingItems<Screenshot>,
    onClick: (Screenshot?) -> Unit = {},
) {
    val state = rememberLazyListState()
    LazyRow(
        modifier = modifier,
        state = state,
        contentPadding = contentPadding,
        content = {
            items(screenshots.itemCount) { index ->
                val screenshot = screenshots[index]
                Screenshot(
                    modifier = Modifier
                        .aspectRatio(9 / 16f)
                        .padding(screenshotPadding),
                    screenshot = screenshot,
                    onClick = onClick,
                )
            }
        }
    )
}