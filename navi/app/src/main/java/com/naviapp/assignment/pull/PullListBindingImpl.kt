package com.naviapp.assignment.pull

import android.content.Context
import android.view.LayoutInflater
import com.naviapp.assignment.R
import com.naviapp.assignment.util.BindingImpl
import com.naviapp.assignment.databinding.ActivityPullListBinding
import com.naviapp.assignment.util.VerticalDividerItemDecoration

class PullListBindingImpl(context: Context) : BindingImpl<ActivityPullListBinding>(
    layoutId = R.layout.activity_pull_list,
    layoutInflater = LayoutInflater.from(context),
) {

    val adapter = PullPagingDataAdapter()
    private val decor = VerticalDividerItemDecoration(context)

    init {
        binding.pullList.adapter = adapter
        binding.pullList.addItemDecoration(decor)
    }

}