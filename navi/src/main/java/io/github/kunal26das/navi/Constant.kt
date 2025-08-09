package io.github.kunal26das.navi

import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.remoteConfig

object Constant {
    const val KEY_REPO = "repo"
    const val INITIAL_PAGE = 1
    const val DEFAULT_PER_PAGE = 10
    const val BASE_URL = "https://api.github.com/"
    const val USER_ID = "kunal26das"
    private const val KEY_GITHUB_TOKEN = "github_token"
    val token get() = Firebase.remoteConfig.getString(KEY_GITHUB_TOKEN)
}