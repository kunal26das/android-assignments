package io.github.kunal26das.navi.pull

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import io.github.kunal26das.navi.databinding.ItemPullBinding
import io.github.kunal26das.navi.model.Pull

class PullPagingDataAdapter : PagingDataAdapter<Pull, PullViewHolder>(Pull.DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullViewHolder {
        val context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        val binding = ItemPullBinding.inflate(layoutInflater, parent, false)
        return PullViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PullViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}