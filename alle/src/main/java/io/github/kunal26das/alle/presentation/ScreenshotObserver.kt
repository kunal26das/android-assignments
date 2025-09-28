package io.github.kunal26das.alle.presentation

import android.content.Context
import android.database.ContentObserver
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import io.github.kunal26das.alle.Constants
import io.github.kunal26das.alle.work.ScreenshotsWorker

class ScreenshotObserver(
    private val context: Context
) : ContentObserver(Handler(Looper.getMainLooper())) {
    override fun onChange(selfChange: Boolean, uri: Uri?) {
        super.onChange(selfChange, uri)
        if (uri != null && uri == MediaStore.Images.Media.EXTERNAL_CONTENT_URI) {
            if (uri.path?.contains(Constants.SCREENSHOT, ignoreCase = false) == true) {
                ScreenshotsWorker.enqueue(context)
            }
        }
    }
}
