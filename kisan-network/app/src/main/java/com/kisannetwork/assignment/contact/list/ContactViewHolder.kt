package com.kisannetwork.assignment.contact.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kisannetwork.assignment.contact.Contact
import com.kisannetwork.assignment.contact.OnContactClickListener
import com.kisannetwork.assignment.databinding.ItemContactBinding

class ContactViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
) {

    private val binding = DataBindingUtil.getBinding<ItemContactBinding>(itemView)

    @SuppressLint("SetTextI18n")
    fun bind(contact: Contact?, onContactClickListener: OnContactClickListener) {
        binding?.contact = contact
        binding?.root?.setOnClickListener {
            onContactClickListener.invoke(contact)
        }
    }

}