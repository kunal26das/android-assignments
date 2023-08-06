package io.github.kunal26das.navi.util

import android.icu.text.SimpleDateFormat
import java.util.Locale

class GithubDateFormat : SimpleDateFormat(
    "yyyy-MM-dd'T'HH:mm:ss'Z'",
    Locale.getDefault()
)