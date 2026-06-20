package io.github.kunal26das.kisan_network.message.compose

import android.Manifest.permission.SEND_SMS
import android.content.Context
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import io.github.kunal26das.common.Activity
import io.github.kunal26das.kisan_network.R
import io.github.kunal26das.kisan_network.contact.Contact
import io.github.kunal26das.kisan_network.contact.Contact.Companion.KEY_CONTACT
import io.github.kunal26das.kisan_network.message.Message
import kotlin.random.Random

@AndroidEntryPoint
class NewMessageActivity : Activity() {

    private val viewModel by viewModels<NewMessageViewModel>()
    private val otp = Random.nextInt(100000, 1000000).toString()
    private val contact by lazy { intent.getSerializableExtra(KEY_CONTACT, Contact::class.java) }

    @Composable
    override fun Content() {
        var message by remember { mutableStateOf(getString(R.string.hi_your_otp_is_this, otp)) }
        var error by remember { mutableStateOf<String?>(null) }
        val requestSendSms = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { sendSms(message) { error = it } }

        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            isError = error != null,
            supportingText = error?.let { { Text(it) } },
            trailingIcon = {
                TextButton(
                    onClick = {
                        if (message.isNotEmpty()) requestSendSms.launch(SEND_SMS)
                    },
                ) {
                    Text(stringResource(R.string.send_message))
                }
            },
        )
    }

    private fun sendSms(message: String, onError: (String?) -> Unit) {
        try {
            viewModel.sendSms(contact, message)
            viewModel.insertMessage(Message(contact?.displayName ?: "", otp)) {
                finish()
            }
        } catch (e: Throwable) {
            onError(e.message)
        }
    }

    class Contract : ActivityResultContract<Contact, Boolean>() {
        override fun createIntent(context: Context, input: Contact): Intent {
            return Intent(context, NewMessageActivity::class.java).apply {
                putExtra(KEY_CONTACT, input)
            }
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Boolean {
            return resultCode == RESULT_OK
        }
    }
}
