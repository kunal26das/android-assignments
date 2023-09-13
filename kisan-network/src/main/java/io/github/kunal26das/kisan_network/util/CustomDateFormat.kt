package io.github.kunal26das.kisan_network.util

import java.text.SimpleDateFormat
import java.util.Locale

class CustomDateFormat : SimpleDateFormat(
    "EEE, d MMM yyyy HH:mm:ss",
    Locale.getDefault()
)