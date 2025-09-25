package io.github.kunal26das.kutumb

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import io.github.kunal26das.kutumb.Constant.KEY_TIME
import io.github.kunal26das.kutumb.databinding.ActivityTimerBinding

class CountDownActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTimerBinding
    private val receiver by lazy { TimerReceiver() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_timer)
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(receiver, IntentFilter(KEY_TIME))
        receiver.setOnTimeReceiveListener {
            binding.timer.text = Constant.parse(it)
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