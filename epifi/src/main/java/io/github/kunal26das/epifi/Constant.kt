package io.github.kunal26das.epifi

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig

object Constant {
    const val BASE_URL = "http://www.omdbapi.com/"
    private const val KEY_OMDB_API_KEY = "omdb_api_key"
    val apiKey get() = Firebase.remoteConfig.getString(KEY_OMDB_API_KEY)
}