package io.github.kunal26das.kutumb

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.CountDownTimer
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import io.github.kunal26das.kutumb.Constant.ID_NOTIFICATION
import io.github.kunal26das.kutumb.Constant.KEY_TIME

class Timer(
    private val context: Context,
    timeLong: Long,
    private val builder: NotificationCompat.Builder
) : CountDownTimer(timeLong, 1000) {

    private val localBroadcastReceiver by lazy {
        LocalBroadcastManager.getInstance(context)
    }
    private val notificationManager by lazy {
        NotificationManagerCompat.from(context)
    }

    override fun onTick(p0: Long) {
        val intent = Intent(KEY_TIME)
        intent.putExtra(KEY_TIME, p0)
        localBroadcastReceiver.sendBroadcast(intent)
        builder.setContentText(Constant.parse(p0))
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            notificationManager.notify(ID_NOTIFICATION, builder.build())
        }
    }

    override fun onFinish() {
        val intent = Intent(KEY_TIME)
        intent.putExtra(KEY_TIME, 0L)
        localBroadcastReceiver.sendBroadcast(intent)
    }
}