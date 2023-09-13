package io.github.kunal26das.kisan_network.contact.list

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import io.github.kunal26das.common.OnItemClickListener
import io.github.kunal26das.kisan_network.contact.Contact
import io.github.kunal26das.kisan_network.contact.ContactDiffCallback

class ContactListAdapter :
    PagingDataAdapter<Contact, ContactViewHolder>(ContactDiffCallback()) {

    private var onContactClickListener: OnItemClickListener<Contact>? = null

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
        onContactClickListener: OnItemClickListener<Contact>
    ): ContactListAdapter {
        this.onContactClickListener = onContactClickListener
        return this
    }
}