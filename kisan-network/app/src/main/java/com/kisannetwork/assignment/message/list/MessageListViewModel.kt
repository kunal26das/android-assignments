package com.kisannetwork.assignment.message.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kisannetwork.assignment.message.Message
import com.kisannetwork.assignment.message.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MessageListViewModel @Inject constructor(
    private val messageRepository: MessageRepository
) : ViewModel() {

    fun getMessageList(): LiveData<List<Message>> {
        return messageRepository.getMessageList().asLiveData()
    }

}