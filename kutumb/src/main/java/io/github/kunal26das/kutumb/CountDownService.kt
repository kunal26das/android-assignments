package io.github.kunal26das.kutumb

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import io.github.kunal26das.kutumb.Constant.CHANNEL_TIMER
import io.github.kunal26das.kutumb.Constant.ID_NOTIFICATION
import io.github.kunal26das.kutumb.Constant.KEY_TIME

class CountDownService : Service() {

    private lateinit var timer: Timer
    private val localBroadcastManager by lazy {
        LocalBroadcastManager.getInstance(this)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val timeInMinutes = intent?.getIntExtra(KEY_TIME, 0) ?: 0
        val timeLong = timeInMinutes * 60 * 1000L
        val builder = getNotificationBuilder()
        timer = Timer(this, timeLong, builder)
        timer.start()
        startNotification(builder)
        isRunning = true
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        isRunning = false
        timer.cancel()
        super.onDestroy()
        val timerInfoIntent = Intent(KEY_TIME)
        timerInfoIntent.putExtra(KEY_TIME, 0)
        localBroadcastManager.sendBroadcast(timerInfoIntent)
    }

    private fun getNotificationBuilder(): NotificationCompat.Builder {
        val notificationIntent = Intent(this, CountDownActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0,
            notificationIntent, PendingIntent.FLAG_IMMUTABLE
        )
        return NotificationCompat.Builder(this, CHANNEL_TIMER)
//            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentText("Counter Down Service")
            .setContentIntent(pendingIntent)
    }

    private fun startNotification(builder: NotificationCompat.Builder) {
        startForeground(ID_NOTIFICATION, builder.build())
    }

    companion object {
        var isRunning: Boolean = false
            private set
    }
}