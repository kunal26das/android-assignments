package io.github.kunal26das.navi.repo

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import dagger.hilt.android.AndroidEntryPoint
import io.github.kunal26das.common.Activity
import io.github.kunal26das.navi.R
import io.github.kunal26das.navi.pull.PullListActivity

@AndroidEntryPoint
class RepoListActivity : Activity() {

    private val viewModel by viewModels<RepoListViewModel>()
    private val pullRequestActivity =
        registerForActivityResult(PullListActivity.Contract()) {}

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val repos = viewModel.repoListPager.collectAsLazyPagingItems()
        Scaffold(
            topBar = { TopAppBar(title = { Text(stringResource(R.string.repositories)) }) },
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
            ) {
                items(repos.itemCount) { index ->
                    val repo = repos[index] ?: return@items
                    Text(
                        text = repo.name.orEmpty(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { pullRequestActivity.launch(repo) }
                            .padding(16.dp),
                    )
                    HorizontalDivider()
                }
            }
        }
    }

    class Contract : ActivityResultContract<Any?, Boolean>() {
        override fun createIntent(context: Context, input: Any?): Intent {
            return Intent(context, RepoListActivity::class.java)
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Boolean {
            return resultCode == RESULT_OK
        }
    }
}
