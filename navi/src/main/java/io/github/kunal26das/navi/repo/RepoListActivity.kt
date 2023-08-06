package io.github.kunal26das.navi.repo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.viewModels
import io.github.kunal26das.navi.pull.PullListActivity
import dagger.hilt.android.AndroidEntryPoint
import io.github.kunal26das.navi.R

@AndroidEntryPoint
class RepoListActivity : AppCompatActivity() {

    private val viewModel by viewModels<RepoListViewModel>()
    private val pullRequestActivity =
        registerForActivityResult(PullListActivity.Contract()) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.repositories)
        val binding = RepoListBindingImpl(this)
        setContentView(binding.root)
        binding.adapter.setOnRepoClickListener {
            pullRequestActivity.launch(it)
        }
        viewModel.repoListPager.observe(this) {
            binding.adapter.submitData(lifecycle, it)
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