package io.github.kunal26das.kisan_network.contact.list

import android.Manifest.permission.READ_CONTACTS
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import io.github.kunal26das.kisan_network.R
import io.github.kunal26das.kisan_network.contact.ContactInfoDialog
import io.github.kunal26das.kisan_network.databinding.FragmentContactListBinding
import io.github.kunal26das.kisan_network.message.compose.NewMessageActivity
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ContactListFragment : Fragment() {

    private lateinit var binding: FragmentContactListBinding
    private val viewModel by viewModels<ContactListViewModel>()

    private val contactListAdapter = ContactListAdapter()

    private val canRequestReadContactsPermission
        get() = ActivityCompat.shouldShowRequestPermissionRationale(
            requireActivity(), READ_CONTACTS
        )

    private val settings =
        registerForActivityResult(io.github.kunal26das.common.SettingsContract()) {}
    private val newMessageActivity = registerForActivityResult(NewMessageActivity.Contract()) {}
    private val requestReadContactsPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            when {
                it -> getContactList()
                canRequestReadContactsPermission -> requestReadContactsPermission()
                else -> {
                    settings.launch(READ_CONTACTS)
                    Toast.makeText(
                        context,
                        getString(R.string.need_read_contacts_permission),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.contactList.adapter = contactListAdapter.setOnContactClickListener {
            if (it != null) ContactInfoDialog(requireContext(), it) { _, _ ->
                newMessageActivity.launch(it)
            }.show()
        }
        binding.contactList.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))

        requestReadContactsPermission()
    }

    private fun requestReadContactsPermission() {
        requestReadContactsPermission.launch(READ_CONTACTS)
    }

    private fun getContactList() {
        viewModel.contactListPager.removeObservers(viewLifecycleOwner)
        viewModel.contactListPager.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                contactListAdapter.submitData(it)
            }
        }
    }

}