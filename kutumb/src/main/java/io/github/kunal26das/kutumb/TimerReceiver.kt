package io.github.kunal26das.kutumb

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import io.github.kunal26das.kutumb.Constant.KEY_TIME

class TimerReceiver : BroadcastReceiver() {

    private var listener: ((Long) -> Unit)? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null && intent.action == KEY_TIME) {
            if (intent.hasExtra(KEY_TIME)) {
                listener?.invoke(intent.getLongExtra(KEY_TIME, 0L))
            }
        }
    }

    fun setOnTimeReceiveListener(listener: (Long) -> Unit) {
        this.listener = listener
    }
}