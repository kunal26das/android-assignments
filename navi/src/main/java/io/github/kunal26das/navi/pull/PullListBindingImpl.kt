package io.github.kunal26das.navi.pull

import android.content.Context
import android.view.LayoutInflater
import io.github.kunal26das.navi.R
import io.github.kunal26das.navi.databinding.ActivityPullListBinding
import io.github.kunal26das.navi.util.BindingImpl
import io.github.kunal26das.navi.util.VerticalDividerItemDecoration

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