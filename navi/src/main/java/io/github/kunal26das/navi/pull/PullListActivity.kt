package io.github.kunal26das.navi.pull

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import dagger.hilt.android.AndroidEntryPoint
import io.github.kunal26das.common.Activity
import io.github.kunal26das.navi.Constant.KEY_REPO
import io.github.kunal26das.navi.R
import io.github.kunal26das.navi.model.Pull
import io.github.kunal26das.navi.model.Repo
import io.github.kunal26das.navi.util.CustomDateFormat
import io.github.kunal26das.navi.util.GithubDateFormat
import java.text.ParseException

@AndroidEntryPoint
class PullListActivity : Activity() {

    private val viewModel by viewModels<PullListViewModel>()
    private val repo by lazy { intent.getSerializableExtra(KEY_REPO, Repo::class.java) }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val pager = remember(repo) { viewModel.getPullPager(repo) }
        val pulls = pager.collectAsLazyPagingItems()
        val currentList by viewModel.currentList.observeAsState()
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Column {
                            Text(stringResource(R.string.closed_pulls))
                            repo?.fullName?.let {
                                Text(text = it, fontSize = 12.sp)
                            }
                        }
                    },
                )
            },
        ) { padding ->
            if (currentList?.isEmpty() == true) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(stringResource(R.string.no_closed_pulls_in_this_repo))
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                ) {
                    items(pulls.itemCount) { index ->
                        val pull = pulls[index] ?: return@items
                        PullItem(pull)
                        HorizontalDivider()
                    }
                }
            }
        }
    }

    class Contract : ActivityResultContract<Repo?, Boolean>() {
        override fun createIntent(context: Context, input: Repo?): Intent {
            return Intent(context, PullListActivity::class.java).apply {
                putExtra(KEY_REPO, input)
            }
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Boolean {
            return resultCode == RESULT_OK
        }
    }
}

private val githubDateFormat = GithubDateFormat()
private val customDateFormat = CustomDateFormat()

private fun formatDate(date: String?): String? {
    return try {
        customDateFormat.format(githubDateFormat.parse(date))
    } catch (_: ParseException) {
        date
    }
}

@Composable
private fun PullItem(pull: Pull) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = pull.title.orEmpty(),
            modifier = Modifier.padding(8.dp),
            fontSize = 16.sp,
        )
        Row(
            modifier = Modifier.padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            AsyncImage(
                model = pull.user?.avatarUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape),
            )
            Text(text = pull.user?.name.orEmpty(), maxLines = 1)
        }
        Text(
            text = stringResource(R.string.created_at, formatDate(pull.createdAt).orEmpty()),
            modifier = Modifier.padding(8.dp),
            maxLines = 1,
        )
        Text(
            text = stringResource(R.string.closed_at, formatDate(pull.closedAt).orEmpty()),
            modifier = Modifier.padding(8.dp),
            maxLines = 1,
        )
    }
}
