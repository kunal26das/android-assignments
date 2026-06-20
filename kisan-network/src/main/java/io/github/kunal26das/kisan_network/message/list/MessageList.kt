package io.github.kunal26das.kisan_network.message.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.kunal26das.kisan_network.util.CustomDateFormat
import java.util.Date

@Composable
fun MessageList(viewModel: MessageListViewModel) {
    val messages by viewModel.messages.collectAsStateWithLifecycle()
    val dateFormat = remember { CustomDateFormat() }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(messages) { message ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Text(text = dateFormat.format(Date(message.timestamp)))
                Text(text = "${message.displayName}: ${message.otp}")
            }
            HorizontalDivider()
        }
    }
}
