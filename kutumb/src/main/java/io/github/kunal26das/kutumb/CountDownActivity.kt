package io.github.kunal26das.kutumb

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.kunal26das.common.Activity
import io.github.kunal26das.kutumb.Constant.KEY_TIME

class CountDownActivity : Activity() {

    private val receiver by lazy { TimerReceiver() }
    private var timer by mutableStateOf("")

    @Composable
    override fun Content() {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)) {
            Text(
                text = stringResource(R.string.remaining_focus_time),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = timer,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(receiver, IntentFilter(KEY_TIME), RECEIVER_NOT_EXPORTED)
        receiver.setOnTimeReceiveListener {
            timer = Constant.parse(it)
            if (it == 0L) finish()
        }
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    class Contract : ActivityResultContract<Any?, Boolean>() {
        override fun createIntent(context: Context, input: Any?): Intent {
            return Intent(context, CountDownActivity::class.java)
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Boolean {
            return resultCode == RESULT_OK
        }
    }
}
