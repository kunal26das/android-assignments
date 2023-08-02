package com.naviapp.assignment.pull

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.naviapp.assignment.databinding.ItemPullBinding
import com.naviapp.assignment.model.Pull
import com.naviapp.assignment.model.Repo
import com.naviapp.assignment.util.OnItemClickListener

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