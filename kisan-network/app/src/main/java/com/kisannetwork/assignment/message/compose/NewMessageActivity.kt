package com.kisannetwork.assignment.message.compose

import android.Manifest.permission.SEND_SMS
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsManager
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kisannetwork.assignment.R
import com.kisannetwork.assignment.contact.Contact
import com.kisannetwork.assignment.contact.Contact.Companion.KEY_CONTACT
import com.kisannetwork.assignment.databinding.ActivityNewMessageBinding
import com.kisannetwork.assignment.message.Message
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class NewMessageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewMessageBinding
    private val viewModel by viewModels<NewMessageViewModel>()

    private val otp = String.format("%06d", Random.nextInt(999999))
    private val message get() = binding.textInputLayout.editText?.text?.toString()
    private val contact by lazy { intent.getParcelableExtra<Contact>(KEY_CONTACT) }

    private val requestSendSmsPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            sendSms()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_message)
        binding.textInputLayout.editText?.setText(getString(R.string.hi_your_otp_is_this, otp))
        binding.textInputLayout.setEndIconOnClickListener {
            if (!message.isNullOrEmpty()) {
                requestSendSmsPermission.launch(SEND_SMS)
            }
        }
    }

    private fun sendSms() {
        try {
            viewModel.sendSms(contact, message)
            viewModel.insertMessage(
                Message(contact?.displayName ?: "", otp)
            ) {
                finish()
            }
        } catch (e: Throwable) {
            binding.textInputLayout.error = e.message
            binding.textInputLayout.isErrorEnabled = true
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