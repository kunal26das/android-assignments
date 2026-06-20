package io.github.kunal26das.kisan_network.message.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.kunal26das.kisan_network.message.Message
import io.github.kunal26das.kisan_network.message.MessageRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MessageListViewModel @Inject constructor(
    messageRepository: MessageRepository
) : ViewModel() {

    val messages: StateFlow<List<Message>> = messageRepository.getMessageList()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
}
