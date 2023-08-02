package com.naviapp.assignment.repo

import androidx.recyclerview.widget.RecyclerView
import com.naviapp.assignment.databinding.ItemRepoBinding
import com.naviapp.assignment.model.Repo
import com.naviapp.assignment.util.OnItemClickListener

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