package com.kisannetwork.assignment.home

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kisannetwork.assignment.contact.list.ContactListFragment
import com.kisannetwork.assignment.message.list.MessageListFragment

class FragmentStateAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    private val tabs = Tab.values()

    override fun getItemCount() = tabs.size

    override fun createFragment(
        position: Int
    ) = when (tabs[position]) {
        Tab.Contacts -> ContactListFragment()
        Tab.Messages -> MessageListFragment()
    }

    fun getText(position: Int) = tabs[position].name

}