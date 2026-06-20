package io.github.kunal26das.kisan_network.home

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import io.github.kunal26das.common.Activity
import io.github.kunal26das.kisan_network.contact.list.ContactList
import io.github.kunal26das.kisan_network.contact.list.ContactListViewModel
import io.github.kunal26das.kisan_network.message.compose.NewMessageActivity
import io.github.kunal26das.kisan_network.message.list.MessageList
import io.github.kunal26das.kisan_network.message.list.MessageListViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class KisanNetworkActivity : Activity() {

    private val contactViewModel by viewModels<ContactListViewModel>()
    private val messageViewModel by viewModels<MessageListViewModel>()
    private val newMessageActivity = registerForActivityResult(NewMessageActivity.Contract()) {}

    @Composable
    override fun Content() {
        val tabs = Tab.entries
        val pagerState = rememberPagerState { tabs.size }
        val scope = rememberCoroutineScope()
        Column(modifier = Modifier.fillMaxSize()) {
            TabRow(selectedTabIndex = pagerState.currentPage) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                        text = { Text(tab.name) },
                    )
                }
            }
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
            ) { page ->
                when (tabs[page]) {
                    Tab.Contacts -> ContactList(
                        viewModel = contactViewModel,
                        onSendMessage = { newMessageActivity.launch(it) },
                    )

                    Tab.Messages -> MessageList(viewModel = messageViewModel)
                }
            }
        }
    }

    class Contract : ActivityResultContract<Any?, Boolean>() {
        override fun createIntent(context: Context, input: Any?): Intent {
            return Intent(context, KisanNetworkActivity::class.java)
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Boolean {
            return resultCode == RESULT_OK
        }
    }
}
