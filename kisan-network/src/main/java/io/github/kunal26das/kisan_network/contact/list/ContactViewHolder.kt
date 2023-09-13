package io.github.kunal26das.kisan_network.contact.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.kunal26das.common.OnItemClickListener
import io.github.kunal26das.kisan_network.contact.Contact
import io.github.kunal26das.kisan_network.databinding.ItemContactBinding

class ContactViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
) {

    private val binding = DataBindingUtil.getBinding<ItemContactBinding>(itemView)

    @SuppressLint("SetTextI18n")
    fun bind(contact: Contact?, onContactClickListener: OnItemClickListener<Contact>) {
        binding?.contact = contact
        binding?.root?.setOnClickListener {
            onContactClickListener.invoke(contact)
        }
    }
}