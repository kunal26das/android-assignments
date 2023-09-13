package io.github.kunal26das.navi.repo

import androidx.recyclerview.widget.RecyclerView
import io.github.kunal26das.common.OnItemClickListener
import io.github.kunal26das.navi.databinding.ItemRepoBinding
import io.github.kunal26das.navi.model.Repo

class RepoViewHolder(
    private val binding: ItemRepoBinding
) : RecyclerView.ViewHolder(binding.root) {

    private var onRepoClickListener: OnItemClickListener<Repo>? = null

    fun bind(repo: Repo?): RepoViewHolder {
        binding.repo = repo
        binding.root.setOnClickListener {
            onRepoClickListener?.invoke(repo)
        }
        return this
    }

    fun setOnRepoClickListener(
        onRepoClickListener: OnItemClickListener<Repo>
    ): RepoViewHolder {
        this.onRepoClickListener = onRepoClickListener
        return this
    }
}