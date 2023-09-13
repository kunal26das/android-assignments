package io.github.kunal26das.navi.repo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import io.github.kunal26das.common.OnItemClickListener
import io.github.kunal26das.navi.databinding.ItemRepoBinding
import io.github.kunal26das.navi.model.Repo

class RepoPagingDataAdapter : PagingDataAdapter<Repo, RepoViewHolder>(Repo.DiffCallback()) {

    private var onRepoClickListener: OnItemClickListener<Repo>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        val binding = ItemRepoBinding.inflate(layoutInflater, parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(getItem(position)).setOnRepoClickListener {
            onRepoClickListener?.invoke(it)
        }
    }

    fun setOnRepoClickListener(
        onRepoClickListener: OnItemClickListener<Repo>
    ) {
        this.onRepoClickListener = onRepoClickListener
    }
}