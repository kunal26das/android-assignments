package com.kisannetwork.assignment.contact.list

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.kisannetwork.assignment.contact.Contact
import com.kisannetwork.assignment.contact.ContactDiffCallback
import com.kisannetwork.assignment.contact.OnContactClickListener

class ContactListAdapter :
    PagingDataAdapter<Contact, ContactViewHolder>(ContactDiffCallback()) {

    private var onContactClickListener: OnContactClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = getItem(position)
        holder.bind(contact) {
            onContactClickListener?.invoke(it)
        }
    }

    fun setOnContactClickListener(
        onContactClickListener: OnContactClickListener
    ): ContactListAdapter {
        this.onContactClickListener = onContactClickListener
        return this
    }

}