package io.github.kunal26das.kisan_network.message.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.kunal26das.kisan_network.databinding.ItemMessageBinding
import io.github.kunal26das.kisan_network.message.Message
import io.github.kunal26das.kisan_network.util.CustomDateFormat
import java.util.Date

class MessageViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
) {

    private val binding = DataBindingUtil.getBinding<ItemMessageBinding>(itemView)
    private val customDateFormat = CustomDateFormat()

    fun bind(message: Message) {
        binding?.message = message
        binding?.timestamp?.text = customDateFormat.format(Date(message.timestamp))
    }
}