package com.kisannetwork.assignment.message.compose

import android.telephony.SmsManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kisannetwork.assignment.contact.Contact
import com.kisannetwork.assignment.message.Message
import com.kisannetwork.assignment.message.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewMessageViewModel @Inject constructor(
    private val smsManager: SmsManager,
    private val messageRepository: MessageRepository
): ViewModel() {

    fun sendSms(contact: Contact?, message: String?) {
        smsManager.sendTextMessage(
            contact?.number,
            null,
            message,
            null,
            null
        )
    }

    fun insertMessage(
        message: Message,
        onComplete: () -> Unit,
    ) {
        viewModelScope.launch {
            messageRepository.insertMessage(message)
            onComplete.invoke()
        }
    }

}