package com.naviapp.assignment.pull

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.naviapp.assignment.R
import com.naviapp.assignment.databinding.ItemPullBinding
import com.naviapp.assignment.model.Pull
import com.naviapp.assignment.util.CustomDateFormat
import com.naviapp.assignment.util.GithubDateFormat
import java.text.ParseException

class PullViewHolder(
    private val binding: ItemPullBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val githubDateFormat = GithubDateFormat()
    private val customDateFormat = CustomDateFormat()
    private val context get() = itemView.context

    fun bind(pull: Pull?) {
        binding.pull = pull
        binding.closedAt.text = context.getString(R.string.closed_at, formatDate(pull?.closedAt))
        binding.createdAt.text = context.getString(R.string.created_at, formatDate(pull?.createdAt))
        binding.avatarUrl.load(pull?.user?.avatarUrl) {
            transformations(CircleCropTransformation())
        }
    }

    private fun formatDate(date: String?): String? {
        return try {
            customDateFormat.format(githubDateFormat.parse(date))
        } catch (e: ParseException) {
            date
        }
    }

}