package io.github.kunal26das.epifi.model

import android.os.Parcelable
import io.github.kunal26das.epifi.preference.PreferenceModule.KEY_SEARCH_QUERY
import io.github.kunal26das.epifi.preference.PreferenceModule.KEY_SEARCH_TYPE
import io.github.kunal26das.epifi.preference.PreferenceModule.KEY_SHOW_BOOKMARKS
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Preferences(
    @SerializedName(KEY_SEARCH_QUERY)
    var searchQuery: String? = null,

    @SerializedName(KEY_SEARCH_TYPE)
    var searchType: SearchType? = null,

    @SerializedName(KEY_SHOW_BOOKMARKS)
    var showBookmarks: Boolean = false,
) : Parcelable
