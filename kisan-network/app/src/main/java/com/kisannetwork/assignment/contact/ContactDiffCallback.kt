package com.kisannetwork.assignment.contact

import androidx.recyclerview.widget.DiffUtil
import com.kisannetwork.assignment.contact.Contact

class ContactDiffCallback : DiffUtil.ItemCallback<Contact>() {
    override fun areItemsTheSame(
        oldItem: Contact,
        newItem: Contact
    ): Boolean {
        return oldItem.phoneNumber == newItem.phoneNumber
    }

    override fun areContentsTheSame(
        oldItem: Contact,
        newItem: Contact
    ): Boolean {
        return oldItem == newItem
    }
}