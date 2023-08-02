package com.kisannetwork.assignment.message.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kisannetwork.assignment.databinding.ItemMessageBinding
import com.kisannetwork.assignment.message.Message
import java.text.SimpleDateFormat
import java.util.*

class MessageViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
) {

    private val binding = DataBindingUtil.getBinding<ItemMessageBinding>(itemView)
    private val simpleDateFormat = SimpleDateFormat(SIMPLE_DATE_FORMAT, Locale.getDefault())

    fun bind(message: Message) {
        binding?.message = message
        binding?.timestamp?.text = simpleDateFormat.format(Date(message.timestamp))
    }

    companion object {
        private const val SIMPLE_DATE_FORMAT = "EEE, d MMM yyyy HH:mm:ss"
    }

}