package com.naviapp.assignment.pull

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.naviapp.assignment.Constant.KEY_REPO
import com.naviapp.assignment.R
import com.naviapp.assignment.model.Repo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PullListActivity : AppCompatActivity() {

    private val viewModel by viewModels<PullListViewModel>()
    private val repo by lazy { intent.getParcelableExtra<Repo>(KEY_REPO) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.closed_pulls)
        supportActionBar?.subtitle = repo?.fullName
        val binding = PullListBindingImpl(this)
        setContentView(binding.root)
        viewModel.getPullPager(repo).observe(this) {
            binding.adapter.submitData(lifecycle, it)
        }
        viewModel.currentList.observe(this) {
            if (it.isNullOrEmpty()) {
                Snackbar.make(
                    binding.root,
                    getString(R.string.no_closed_pulls_in_this_repo),
                    Snackbar.LENGTH_INDEFINITE,
                ).show()
            }
        }
    }

    class Contract : ActivityResultContract<Repo, Boolean>() {
        override fun createIntent(context: Context, input: Repo): Intent {
            return Intent(context, PullListActivity::class.java).apply {
                putExtra(KEY_REPO, input)
            }
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Boolean {
            return resultCode == RESULT_OK
        }
    }

}