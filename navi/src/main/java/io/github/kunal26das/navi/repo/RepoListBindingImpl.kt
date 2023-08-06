package io.github.kunal26das.navi.repo

import android.content.Context
import android.view.LayoutInflater
import io.github.kunal26das.navi.R
import io.github.kunal26das.navi.databinding.ActivityRepoListBinding
import io.github.kunal26das.navi.util.BindingImpl
import io.github.kunal26das.navi.util.VerticalDividerItemDecoration

class RepoListBindingImpl(
    context: Context
) : BindingImpl<ActivityRepoListBinding>(
    layoutId = R.layout.activity_repo_list,
    layoutInflater = LayoutInflater.from(context)
) {

    val adapter = RepoPagingDataAdapter()
    private val decor = VerticalDividerItemDecoration(context)

    init {
        binding.repoList.adapter = adapter
        binding.repoList.addItemDecoration(decor)
    }
}