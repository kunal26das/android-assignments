package io.github.kunal26das.navi.pull

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import io.github.kunal26das.navi.R
import io.github.kunal26das.navi.databinding.ItemPullBinding
import io.github.kunal26das.navi.model.Pull
import io.github.kunal26das.navi.util.CustomDateFormat
import io.github.kunal26das.navi.util.GithubDateFormat
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