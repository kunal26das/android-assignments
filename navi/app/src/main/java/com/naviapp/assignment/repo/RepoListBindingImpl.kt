package com.naviapp.assignment.repo

import android.content.Context
import android.view.LayoutInflater
import com.naviapp.assignment.R
import com.naviapp.assignment.util.BindingImpl
import com.naviapp.assignment.databinding.ActivityRepoListBinding
import com.naviapp.assignment.util.VerticalDividerItemDecoration

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