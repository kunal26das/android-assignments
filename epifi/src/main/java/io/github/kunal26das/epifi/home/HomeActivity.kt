package io.github.kunal26das.epifi.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import dagger.hilt.android.AndroidEntryPoint
import io.github.kunal26das.epifi.R
import io.github.kunal26das.epifi.model.Element
import io.github.kunal26das.epifi.model.SearchType
import io.github.kunal26das.epifi.ui.DetailInfo
import io.github.kunal26das.epifi.ui.EpifiColors
import io.github.kunal26das.epifi.ui.EpifiTheme
import io.github.kunal26das.epifi.ui.Gilroy
import io.github.kunal26das.epifi.ui.Inter

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            EpifiTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .safeDrawingPadding(),
                    ) {
                        Content()
                    }
                }
            }
        }
    }

    @Composable
    private fun Content() {
        val elements = viewModel.pager.collectAsLazyPagingItems()
        val searchType by viewModel.searchType.observeAsState()
        val showBookmarks by viewModel.showBookmarks.observeAsState()
        val query by viewModel.searchQuery.observeAsState()
        val detail by viewModel.detail.collectAsState()
        val detailLoading by viewModel.detailLoading.collectAsState()
        val bookmarks = remember { mutableStateMapOf<String, Boolean>() }

        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_abda),
                    contentDescription = null,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp),
                )
                IconButton(onClick = { viewModel.setShowBookmarks(showBookmarks != true) }) {
                    Icon(
                        painter = painterResource(
                            if (showBookmarks == true) R.drawable.ic_bookmark_filled
                            else R.drawable.ic_bookmark_outlined
                        ),
                        contentDescription = stringResource(R.string.bookmarks),
                        tint = Color.White,
                    )
                }
            }
            TextField(
                value = query.orEmpty(),
                onValueChange = { viewModel.setSearchQuery(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = {
                    Text(
                        text = stringResource(R.string.search_movie_or_show),
                        color = EpifiColors.Hint,
                        fontFamily = Gilroy,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_search),
                        contentDescription = null,
                        tint = EpifiColors.Hint,
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(24.dp),
                textStyle = androidx.compose.ui.text.TextStyle(
                    fontFamily = Gilroy,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = EpifiColors.SearchBox,
                    unfocusedContainerColor = EpifiColors.SearchBox,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White,
                ),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                SearchTypeButton(stringResource(R.string.home), searchType == null) {
                    viewModel.setSearchType(null)
                }
                SearchTypeButton(stringResource(R.string.movies), searchType == SearchType.movie) {
                    viewModel.setSearchType(SearchType.movie)
                }
                SearchTypeButton(stringResource(R.string.series), searchType == SearchType.series) {
                    viewModel.setSearchType(SearchType.series)
                }
                SearchTypeButton(
                    stringResource(R.string.episodes),
                    searchType == SearchType.episode,
                ) {
                    viewModel.setSearchType(SearchType.episode)
                }
            }
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                verticalItemSpacing = 8.dp,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(elements.itemCount) { index ->
                    val element = elements[index] ?: return@items
                    LaunchedEffect(element.imdbId) {
                        bookmarks[element.imdbId] = viewModel.isBookmarked(element)
                    }
                    ElementCard(
                        element = element,
                        bookmarked = bookmarks[element.imdbId] ?: element.isBookmarked,
                        onClick = { viewModel.loadDetail(element) },
                        onBookmark = {
                            val newValue = !(bookmarks[element.imdbId] ?: element.isBookmarked)
                            bookmarks[element.imdbId] = newValue
                            element.isBookmarked = newValue
                            viewModel.toggleBookmark(element)
                        },
                    )
                }
            }
        }

        detail?.let {
            DetailInfo(
                element = it,
                loading = detailLoading,
                onDismiss = { viewModel.clearDetail() },
            )
        }
    }

    class Contract : ActivityResultContract<Any?, Boolean>() {
        override fun createIntent(context: Context, input: Any?): Intent {
            return Intent(context, HomeActivity::class.java)
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Boolean {
            return resultCode == RESULT_OK
        }
    }
}

@Composable
private fun SearchTypeButton(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        color = if (selected) EpifiColors.ButtonSelected else EpifiColors.SearchBox,
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
            color = if (selected) Color.White else EpifiColors.Subtitle,
            fontFamily = Gilroy,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
        )
    }
}

@Composable
private fun ElementCard(
    element: Element,
    bookmarked: Boolean,
    onClick: () -> Unit,
    onBookmark: () -> Unit,
) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)),
        ) {
            AsyncImage(
                model = element.poster,
                contentDescription = element.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.7f)
                    .clickable { onClick() },
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.7f)
                    .background(
                        Brush.verticalGradient(
                            0f to Color.Transparent,
                            1f to EpifiColors.PosterScrim,
                        )
                    ),
            )
            IconButton(
                onClick = onBookmark,
                modifier = Modifier.align(Alignment.TopEnd),
            ) {
                Icon(
                    painter = painterResource(
                        if (bookmarked) R.drawable.ic_bookmark_filled
                        else R.drawable.ic_bookmark_outlined
                    ),
                    contentDescription = null,
                    tint = Color.White,
                )
            }
        }
        Text(
            text = element.title,
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.White,
            fontFamily = Gilroy,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
        )
        element.searchType?.let {
            Text(
                text = it.name,
                modifier = Modifier.padding(horizontal = 4.dp),
                maxLines = 1,
                color = EpifiColors.Subtitle,
                fontFamily = Inter,
                fontSize = 12.sp,
            )
        }
    }
}
