package com.kisannetwork.assignment.message.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kisannetwork.assignment.message.Message

class MessageAdapter(
    private val messages: List<Message>
) : RecyclerView.Adapter<MessageViewHolder>() {

    override fun getItemCount() = messages.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(parent)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(messages[position])
    }
}