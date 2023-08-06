package io.github.kunal26das.navi.util

import android.icu.text.SimpleDateFormat
import java.util.Locale

class CustomDateFormat : SimpleDateFormat(
    "EEE, MMM d, ''yy",
    Locale.getDefault()
)