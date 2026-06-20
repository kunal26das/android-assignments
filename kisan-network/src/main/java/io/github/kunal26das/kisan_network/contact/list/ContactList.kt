package io.github.kunal26das.kisan_network.contact.list

import android.Manifest.permission.READ_CONTACTS
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.paging.compose.collectAsLazyPagingItems
import io.github.kunal26das.kisan_network.R
import io.github.kunal26das.kisan_network.contact.Contact

@Composable
fun ContactList(
    viewModel: ContactListViewModel,
    onSendMessage: (Contact) -> Unit,
) {
    val context = LocalContext.current
    var granted by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context, READ_CONTACTS) ==
                    PackageManager.PERMISSION_GRANTED
        )
    }
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted = it }

    LaunchedEffect(Unit) {
        if (!granted) permissionLauncher.launch(READ_CONTACTS)
    }

    if (!granted) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Button(onClick = { permissionLauncher.launch(READ_CONTACTS) }) {
                Text(stringResource(R.string.need_read_contacts_permission))
            }
        }
        return
    }

    val contacts = viewModel.contactListPager.collectAsLazyPagingItems()
    var selected by remember { mutableStateOf<Contact?>(null) }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(contacts.itemCount) { index ->
            val contact = contacts[index] ?: return@items
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { selected = contact }
                    .padding(16.dp),
            ) {
                Text(text = contact.displayName)
                Text(text = "+${contact.countryCode} ${contact.phoneNumber}")
            }
            HorizontalDivider()
        }
    }

    selected?.let { contact ->
        AlertDialog(
            onDismissRequest = { selected = null },
            title = { Text(stringResource(R.string.contact_info)) },
            text = {
                Text("${contact.displayName}\n+${contact.countryCode} ${contact.phoneNumber}")
            },
            confirmButton = {
                TextButton(onClick = {
                    selected = null
                    onSendMessage(contact)
                }) {
                    Text(stringResource(R.string.send_message))
                }
            },
        )
    }
}
