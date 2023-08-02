package com.naviapp.assignment.repo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.naviapp.assignment.R
import com.naviapp.assignment.pull.PullListActivity
import dagger.hilt.android.AndroidEntryPoint

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

}